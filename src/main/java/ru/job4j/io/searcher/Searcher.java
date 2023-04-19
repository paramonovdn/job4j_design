package ru.job4j.io.searcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Searcher {
    private static String searchFileName;

    private static String searchType;

    public static String getSearchFileName() {
        return searchFileName;
    }

    public static String getSearchType() {
        return searchType;
    }

    public static void argValidation(ArgsName argsName) {
        String argument1 = argsName.get("d");
        String argument2 = argsName.get("n");
        String argument3 = argsName.get("t");
        String argument4 = argsName.get("o");
        if (!Files.isDirectory(Paths.get(argument1))) {
            throw new IllegalArgumentException(String.format("Directory not found: %s", argument1));
        }
        if (("name".equals(argument3) || "mask".equals(argument3)) && !argument2.contains(".")) {
            throw new IllegalArgumentException(String.format("A point is missing in the -n parameter "
                    + "(name.txt or *.txt)- %s", argument2));
        }
        if (!"mask".equals(argument3) && !"name".equals(argument3) && !"regex".equals(argument3)) {
            throw new IllegalArgumentException(String.format("Check the search parameter (mask, name, regex)- %s", argument3));
        }
        if (!argument4.endsWith(".txt") && !argument4.endsWith(".log")) {
            throw new IllegalArgumentException(String.format("Check the name and extension of the log file- %s", argument4));
        }
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException(String.format("Invalid number of arguments- %s. "
                    + "Expected quantity  - 4", args.length));
        }
        ArgsName argsName = ArgsName.of(args);
        Searcher.argValidation(argsName);
        String startDirectory = argsName.get("d");
        searchFileName = argsName.get("n");
        searchType = argsName.get("t");
        String logFile = argsName.get("o");

        Files.walkFileTree(Path.of(startDirectory), new MySimpleFileVisitor());

        try (PrintWriter outfile = new PrintWriter(logFile)) {
            for (String l : MySimpleFileVisitor.foundFiles) {
                outfile.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
