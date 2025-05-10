package com.example;


import java.net.URLDecoder;
import java.net.URLEncoder;

public class BookURLUtils {
    public static String encodeBookUrl(String url) {
        return URLEncoder.encode(url); // Deprecated as of Java 9
    }

    public static String decodeBookUrl(String encodedUrl) {
        return URLDecoder.decode(encodedUrl); // Deprecated as of Java 9
    }
}