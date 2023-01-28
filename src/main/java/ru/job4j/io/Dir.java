package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Dir {
    /*
    * мапа для хранения всех директорий и размера соответственно
     */
    static  Map<String, Long> directorySize = new HashMap<String, Long>();

    public static void main(String[] args) throws IOException {
        File file = new File("c:\\projects");

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("size : %s", file.getTotalSpace()));


        PrintFiles printFiles = new PrintFiles();
        Files.walkFileTree(Paths.get(String.valueOf(file)), printFiles);

        for (Map.Entry<String, Long> pair : directorySize.entrySet()) {
            String key = pair.getKey();
            Long value = pair.getValue();
            System.out.println(String.format("name: %s, size: %s", key, value));
        }

    }
}