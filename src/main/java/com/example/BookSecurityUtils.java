package com.example;

import java.security.cert.X509Certificate;

public class BookSecurityUtils {
    public static String getBookCertificateSubject(X509Certificate cert) {
        return cert.getSubjectDN().toString(); // Deprecated as of Java 9
    }

    public static String getBookCertificateIssuer(X509Certificate cert) {
        return cert.getIssuerDN().toString(); // Deprecated as of Java 9
    }
}