package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {
    public static List<String> foundFiles = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if ("mask".equals(Searcher.searchType)) {
            Searcher.searchFileName = Searcher.searchFileName.substring(1, Searcher.searchFileName.length());
            if (file.getFileName().toString().endsWith(Searcher.searchFileName)) {
                foundFiles.add(file.toAbsolutePath().toString());
            }
        }
        if ("name".equals(Searcher.searchType)) {
            if (file.getFileName().toString().equals(Searcher.searchFileName)) {
                foundFiles.add(file.toAbsolutePath().toString());
            }
        }
        return super.visitFile(file, attrs);
    }
}
