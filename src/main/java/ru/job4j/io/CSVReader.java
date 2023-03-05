package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader {
    private final Map<String, String> values = new HashMap<>();
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");

        List<String> filterArray = List.of(filter.split(","));
        List<Map<String, String>> allLines = new ArrayList<>();


        FileInputStream inputStream = new FileInputStream(path);
        Scanner scanner = new Scanner(inputStream);
        List<String> firstCSVline = List.of(scanner.nextLine().split(delimiter));
        Map<String, String> everyLine = new HashMap<>();
        for (String line : firstCSVline) {
            everyLine.put(line, line);
        }
        allLines.add(everyLine);

        while (scanner.hasNext()) {
            List<String> anotherCSVline = List.of(scanner.nextLine().split(delimiter));
            everyLine = new HashMap<>();
            for (int i = 0; i < anotherCSVline.size(); i++) {
                everyLine.put(firstCSVline.get(i), anotherCSVline.get(i));
            }
            allLines.add(everyLine);
        }
        scanner.close();

        List<String> finalLines = new ArrayList<>();
        for (Map map : allLines) {
            String lines = "";
            for (int i = 0; i < filterArray.size(); i++) {
                if (map.containsKey(filterArray.get(i))) {
                    if (i == filterArray.size() - 1) {
                        lines += map.get(filterArray.get(i));
                        continue;
                    }
                    lines += map.get(filterArray.get(i)) + delimiter;
                }
            }
            finalLines.add(lines);
        }

        if (out.equals("stdout")) {
            for (String l : finalLines) {
                System.out.println(l);
            }
        } else {
            try (PrintWriter outfile = new PrintWriter(out)) {
                for (String l : finalLines) {
                    outfile.println(l);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void argValidation(ArgsName argsName) {
        String argument1 = argsName.get("path");
        String argument2 = argsName.get("delimiter");
        String argument3 = argsName.get("out");
        String argument4 = argsName.get("filter");
        if (!Paths.get(argument1).toFile().isFile()) {
            throw new IllegalArgumentException(String.format("File not found: %s", argument1));
        }
        if (!argument2.equals(";")) {
            throw new IllegalArgumentException(String.format("The delimiter is not defined- %s", argument2));
        }

        List<String> arg3 = List.of(argument3.split("\\."));
        if (!argument3.equals("stdout") && (arg3.get(0) == "" || arg3.size() != 2 || !arg3.get(1).equals("csv"))) {
            throw new IllegalArgumentException(String.format("Check the out parameter- %s", argument3));
        }

        if (argument4 == null) {
            throw new NullPointerException(String.format("Filter argument is null- %s", argument4));
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException(String.format("Invalid number of arguments- %s. "
                    + "Expected quantity  - 4", args.length));
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader.argValidation(argsName);
        CSVReader.handle(argsName);
    }
}
