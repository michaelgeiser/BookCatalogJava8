package com.example;

import java.lang.Byte;
import java.lang.Short;

public class BookNumberUtils {
    public static Byte byteValueOf(byte value) {
        return new Byte(value); // Deprecated as of Java 9
    }

    public static Short shortValueOf(short value) {
        return new Short(value); // Deprecated as of Java 9
    }
}