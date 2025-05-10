package com.example.util;

public class StringUtils {
    public static boolean isValidBookTitle(String title) {
        if (title == null || title.isEmpty()) {
            return false;
        }

        for (int i = 0; i < title.length(); i++) {
            char c = title.charAt(i);
            if (!Character.isJavaLetterOrDigit(c) && !Character.isSpace(c)) {
                return false;
            }
        }

        return true;
    }
}