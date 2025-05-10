package com.example;

public class BookCharacterUtils {
    public static boolean isBookTitleValid(String title) {
        if (title == null || title.isEmpty()) {
            return false;
        }

        for (int i = 0; i < title.length(); i++) {
            char c = title.charAt(i);
            if (!Character.isJavaLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }

        return true;
    }
}
