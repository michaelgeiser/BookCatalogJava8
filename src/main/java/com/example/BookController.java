package com.example;

import okio.BufferedSource;
import okio.Okio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@RestController
@RequestMapping("/api/books")
public class BookController {
    private static final Logger logger = LogManager.getLogger(BookController.class);
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        logger.info("message from log4j");  // This will print your message
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importBooks(@RequestBody String xmlContent) {
        try {
            BookXmlImporter importer = new BookXmlImporter();
            List<Book> books = importer.importFromXml(xmlContent);
            books.forEach(bookRepository::save);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to import books: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationDate(updatedBook.getPublicationDate());
        book.setIsbn(updatedBook.getIsbn());

        Book updatedBookEntity = bookRepository.save(book);
        return ResponseEntity.ok(updatedBookEntity);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadBookFile(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Book book = bookRepository.findById(id).orElse(null);
            if (book != null) {
                book.setContent(file.getBytes());
                bookRepository.save(book);
                return ResponseEntity.ok("File uploaded successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<String> getBookContent(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null && book.getContent() != null) {
            try {
                InputStream inputStream = new ByteArrayInputStream(book.getContent());
                BufferedSource source = Okio.buffer(Okio.source(inputStream));
                String contentString = source.readUtf8();
                source.close();
                return ResponseEntity.ok(contentString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }
}