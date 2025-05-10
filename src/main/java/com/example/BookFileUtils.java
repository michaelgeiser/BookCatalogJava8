package com.example;

import okio.BufferedSource;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BookFileUtils {

    // Java 8 version using Okio 1.17.5
    public static void readFileWithOkioJava8(String filePath) {
        try {
            File file = new File(filePath);
            Source source = Okio.source(file);
            BufferedSource bufferedSource = Okio.buffer(source);
            while (!bufferedSource.exhausted()) {
                System.out.println(bufferedSource.readUtf8Line());
            }
            bufferedSource.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}