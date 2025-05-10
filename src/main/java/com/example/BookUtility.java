package com.example;

import java.math.BigDecimal;
import java.util.Properties;

public class BookUtility {
    public static BigDecimal calculateDiscount(BigDecimal price, int discountPercentage) {
        // Deprecated java.math.BigDecimal methods
        return price.divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal.valueOf(100 - discountPercentage))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static void saveBookProperties(Properties props) {
        // Deprecated java.util.Properties method
        props.save(System.out, "Book Properties");
    }
}