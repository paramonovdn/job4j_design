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

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
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

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (!Files.isDirectory(Paths.get(directory))) {
            throw new IllegalArgumentException(String.format("The directory does not exist: %s", directory));
        }
        List<Path> excludeFiles =  Search.search(Paths.get(directory), p -> p.toFile().getName().endsWith(exclude));
        List<Path> allFiles =  Files.walk(Paths.get(directory)).filter(Files::isRegularFile).collect(Collectors.toList());
        allFiles.removeAll(excludeFiles);
        List<File> filesForArchiving = allFiles.stream().map(f -> f.toFile()).collect(Collectors.toList());

        String myDirectory = "C:\\backups\\" + output;
        Zip zip = new Zip();
        zip.packFiles(filesForArchiving, new File(myDirectory));
    }
}
