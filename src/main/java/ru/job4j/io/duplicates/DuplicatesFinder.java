package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DuplicatesFinder {

    static List<FileProperty> allFiles = new ArrayList<>();
    static Map<FileProperty, Set<Path>> duplicates = new HashMap<>();

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());

        for (int i = 0; i < allFiles.size(); i++) {
            Set<Path> pathes = new HashSet<>();
            for (int j = i + 1; j < allFiles.size(); j++) {
                if (Objects.equals(allFiles.get(i), allFiles.get(j))) {
                    if (!duplicates.containsKey(allFiles.get(i))) {
                        pathes.add(allFiles.get(i).getPath());
                        pathes.add(allFiles.get(j).getPath());
                        duplicates.put(allFiles.get(i), pathes);
                    } else {
                        pathes = duplicates.get(allFiles.get(i));
                        pathes.add(allFiles.get(j).getPath());
                        duplicates.put(allFiles.get(i), pathes);
                    }
                }
            }
        }

        for (Map.Entry<FileProperty, Set<Path>> pair : duplicates.entrySet()) {
            FileProperty key = pair.getKey();
            Set<Path> value = pair.getValue();
            System.out.println(key);

            for (Path path : value) {
                System.out.println(path.toAbsolutePath());
            }
            System.out.println();
        }
    }
}