package ru.job4j.io.output;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile().getPath()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void argValidation(String directory, String exclude, String output) {
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException(String.format("The directory does not exist: %s", directory));
        }
        if (!exclude.startsWith(".") || exclude.length() < 2) {
            throw new IllegalArgumentException(String.format("The extension of the exception files is not defined: %s",
                    exclude));
        }
        int pointPosition = output.indexOf(".");
        String fileName = output.substring(1, pointPosition);
        if (fileName.length() < 1) {
            throw new IllegalArgumentException(String.format("The file name is incorrect: %s",
                    output));
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format("The extension of the final archive must be- *.zip: %s",
                    output));
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        argValidation(directory, exclude, output);
        List<Path> filesForArchiving =  Search.search(Paths.get(directory), p -> !p.toFile().getName().endsWith(exclude));
        String myDirectory = "C:\\backups\\" + output;
        Zip zip = new Zip();
        zip.packFiles(filesForArchiving, new File(myDirectory));
    }
}
