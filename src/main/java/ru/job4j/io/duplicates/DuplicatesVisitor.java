package ru.job4j.io.duplicates;
import ru.job4j.io.Dir;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, LinkedList<Path>> duplicates = new HashMap<>();
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String pathName = file.getFileName().toString();
        Long pathSize = attrs.size();
        Path path = file.toAbsolutePath();
        FileProperty fileProperty = new FileProperty(pathSize, pathName);
        if (duplicates.containsKey(fileProperty)) {
            LinkedList<Path> pathes = duplicates.get(fileProperty);
            pathes.add(path);
            duplicates.put(fileProperty, pathes);
        } else {
            LinkedList<Path> pathes = new LinkedList<>();
            pathes.add(path);
            duplicates.put(fileProperty, pathes);
        }
        return super.visitFile(file, attrs);
    }

    public void printDuplicateMap() {
        for (Map.Entry<FileProperty, LinkedList<Path>> pair : duplicates.entrySet()) {
            FileProperty key = pair.getKey();
            LinkedList<Path> value = pair.getValue();
            if (value.size() > 1) {
                System.out.println(key.getName() + "- " + key.getSize() + " байт");
                for (Path path : value) {
                    System.out.println(path.toAbsolutePath());
                }
                System.out.println();
            }
        }

    }
}