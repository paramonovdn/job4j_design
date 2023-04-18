package ru.job4j.io.searcher;

import ru.job4j.io.searcher.ArgsName;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Searcher {
    public static String searchFileName;
    public static String searchType;

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
        String logFile = "C:\\projects\\job4j_design\\" + argsName.get("o");

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
