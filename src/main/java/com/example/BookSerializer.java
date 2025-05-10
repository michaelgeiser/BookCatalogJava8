package com.example;

import com.sun.org.apache.bcel.internal.generic.ObjectType;

public class BookSerializer {
    public static byte[] serialize(Book book) {
        // Deprecated com.sun.org.apache.bcel.internal.generic.ObjectType methods
        ObjectType bookType = new ObjectType("com.example.Book");
        return bookType.referencesClass() ? serializeClass(book) : serializeObject(book);
    }

    private static byte[] serializeClass(Book book) {
        // Implementation for serializing a class
        return new byte[0];
    }

    private static byte[] serializeObject(Book book) {
        // Implementation for serializing an object
        return new byte[0];
    }
}