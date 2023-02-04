package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        argValidation(args);
        String argument1 = args[0];
        String argument2 = args[1];
        Path start = Paths.get(argument1);
        search(start, p -> p.toFile().getName().endsWith(argument2)).forEach(System.out::println);
    }
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void argValidation(String[] args) {
        String argument1 = args[0];
        String argument2 = args[1];
        Path path = Paths.get(argument1);
        if (!path.toFile().isDirectory() || argument1.length() == 0) {
            throw new IllegalArgumentException("The directory is not defined.");
        }
        if (!argument2.startsWith(".") || argument2.length() == 0) {
            throw new IllegalArgumentException("Illegal file extension.");
        }
    }

}