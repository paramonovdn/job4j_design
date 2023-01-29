package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        /*
        * создание директории и файла
         */
        Path dirZ = Paths.get("path/paths");
        Files.createDirectories(dirZ);
        Path pathY = Path.of("path/paths/path.txt");
        Files.createFile(pathY);

        /*
         * Для поддержки кода, написанного с помощью класса File, существуют методы toFile() и toPath() для перевода
         *  в объекты File и Path:
         */
        Path pathZ = Path.of("path/paths/path.txt");
        File file = pathZ.toFile();
        System.out.println(file);
        Path pathAgain = file.toPath();
        System.out.println(pathAgain);

        /*
         * демонстрация методов работы с объектами типа Path:
         */
        Path dir = Paths.get("path/paths");
        Files.createDirectories(dir);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);
        System.out.println("Файл/директория существует?: " + Files.exists(path));
        System.out.println("Это директория?: " + Files.isDirectory(path));
        System.out.println("Это файл?: " + Files.isRegularFile(path));
        System.out.println("Имя файла: " + path.getFileName());
        System.out.println("Путь к файлу абсолютный?: " + path.isAbsolute());
        System.out.println("Родительская директория файла: " + path.getParent());
        System.out.println("Абсолютный путь к файлу: " + path.toAbsolutePath());
        System.out.println("Абсолютный путь к директории: " + dir.toAbsolutePath());
        System.out.println("Доступен для чтения?: " + Files.isReadable(path));
        System.out.println("Доступен для записи?: " + Files.isWritable(path));

        /*
         * перемещение файла. Начиная с версии JDK 7 появилась возможность переместить файл с помощью метода move().
         */
        Path dirQ = Paths.get("path/paths");
        Files.createDirectories(dirQ);
        Path pathQ = Path.of("path/paths/path.txt");
        Files.createFile(pathQ);
        Files.move(pathQ, Path.of("path/path.txt"));

        /*
         * удаление файла
         */
        Path dirE = Paths.get("path/paths");
        Files.createDirectories(dirE);
        Path pathE = Path.of("path/paths/path.txt");
        Files.createFile(pathE);
        Files.delete(dirE);

        /*
        *В прошлом уроке мы разбирали методы list() и listFiles() класса File. Аналог этих методов в NIO API -
        *  это метод Files.newDirectoryStream()
        * Метод newDirectoryStream() возвращает поток, содержащий файлы и директории, находящиеся в директории
        * path (без вложенных)
         */
        Path dirX = Paths.get("path/paths");
        Files.createDirectories(dirX);
        Path target = Paths.get("path");
        Path path1 = Path.of("path/paths/path1.txt");
        Files.createFile(path1);
        Path path2 = Path.of("path/path2.txt");
        Files.createFile(path2);
        DirectoryStream<Path> paths = Files.newDirectoryStream(target);
        paths.forEach(System.out::println);


    }
}
