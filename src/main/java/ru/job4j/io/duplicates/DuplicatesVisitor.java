package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> duplicates = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String pathName = file.getFileName().toString();
        Long pathSize = attrs.size();
        FileProperty fileProperty = new FileProperty(pathSize, pathName);
        duplicates.putIfAbsent(fileProperty, new ArrayList<>());
        duplicates.get(fileProperty).add(file);
        return super.visitFile(file, attrs);
    }

    public void printDuplicateMap() {
        for (Map.Entry<FileProperty, List<Path>> pair : duplicates.entrySet()) {
            FileProperty key = pair.getKey();
            List<Path> value = pair.getValue();
            if (value.size() > 1) {
                System.out.println(key.getName() + "- " + key.getSize() + " ����");
                for (Path path : value) {
                    System.out.println(path.toAbsolutePath());
                }
                System.out.println();
            }
        }

    }
}