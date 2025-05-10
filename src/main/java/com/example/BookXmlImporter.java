package com.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookXmlImporter {

    public List<Book> importFromXml(String xmlContent) throws ParserConfigurationException, IOException, SAXException {
        List<Book> books = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource source = new InputSource(new StringReader(xmlContent));
        Document document = builder.parse(source);

        NodeList bookNodes = document.getElementsByTagName("book");
        for (int i = 0; i < bookNodes.getLength(); i++) {
            Element bookElement = (Element) bookNodes.item(i);
            Book book = parseBookFromElement(bookElement);
            books.add(book);
        }

        return books;
    }

    private Book parseBookFromElement(Element bookElement) {
        String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
        String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
        String publicationDateStr = getPublicationDateString(bookElement);
        LocalDate publicationDate = parsePublicationDate(publicationDateStr);
        String isbn = bookElement.getElementsByTagName("isbn").item(0).getTextContent();

        return new Book(title, author, publicationDate, isbn);
    }

    private String getPublicationDateString(Element bookElement) {
        NodeList publicationDateNodes = bookElement.getElementsByTagName("publicationDate");
        if (publicationDateNodes.getLength() > 0) {
            return publicationDateNodes.item(0).getTextContent();
        }
        return null;
    }

    private LocalDate parsePublicationDate(String publicationDateStr) {
        if (publicationDateStr != null) {
            return LocalDate.parse(publicationDateStr);
        }
        // Default to January 1, 1999, if publicationDateStr is not present
        Date defaultDate = new Date(1999, 0, 1);
        return defaultDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}