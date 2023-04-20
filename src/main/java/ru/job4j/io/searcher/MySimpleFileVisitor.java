package ru.job4j.io.searcher;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class MySimpleFileVisitor extends SimpleFileVisitor<Path> {
    public static List<String> foundFiles = new ArrayList<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if ("mask".equals(Searcher.getSearchType())) {
            String mask = Searcher.getSearchFileName().replace(".", "\\.")
                    .replace("?", ".?")
                    .replace("*", ".+");
            Pattern pattern = Pattern.compile(mask);
            Predicate<Path> predicate = p -> {
                String fileName = p.toFile().getName();
                return pattern.matcher(fileName).find();
            };
            if (predicate.test(file)) {
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
