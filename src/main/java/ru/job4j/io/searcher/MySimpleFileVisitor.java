package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {
    public static List<String> foundFiles = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if ("mask".equals(Searcher.getSearchType())) {
            if (file.getFileName().toString().matches("." + Searcher.getSearchFileName())) {
                foundFiles.add(file.toAbsolutePath().toString());
            }
        }
        if ("name".equals(Searcher.getSearchType())) {
            if (file.getFileName().toString().equals(Searcher.getSearchFileName())) {
                foundFiles.add(file.toAbsolutePath().toString());
            }
        }
        return super.visitFile(file, attrs);
    }
}
