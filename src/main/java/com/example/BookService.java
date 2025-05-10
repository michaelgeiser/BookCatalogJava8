package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.logging.Logger;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        LOGGER.info("Saving book: " + book);
        Book savedBook = bookRepository.save(book);
        LOGGER.info("Book saved: " + savedBook);
        return savedBook;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}