package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static java.nio.file.FileVisitResult.CONTINUE;

public class PrintFiles implements FileVisitor<Path> {
    String prevPathName = null;
    Long size = 0L;
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String currentPathName = file.getName(1).toString();
        /*
        *по имени файла или папки первого уровня (file.getName(1).toString()) помещаем в мапу
        * если visitFile возвращает все файлы из одного каталога, size по- нарастающей суммируем
        * если каталог меняется то size обнуляем
         */
        if (Objects.equals(prevPathName, currentPathName)) {
            size += attrs.size();
            Dir.directorySize.put(currentPathName, size);
            prevPathName = currentPathName;
        } else {
            size = 0L;
            size = attrs.size();
            Dir.directorySize.put(currentPathName, size);
            prevPathName = currentPathName;
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}