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
        * �������� ���������� � �����
         */
        Path dirZ = Paths.get("path/paths");
        Files.createDirectories(dirZ);
        Path pathY = Path.of("path/paths/path.txt");
        Files.createFile(pathY);

        /*
         * ��� ��������� ����, ����������� � ������� ������ File, ���������� ������ toFile() � toPath() ��� ��������
         *  � ������� File � Path:
         */
        Path pathZ = Path.of("path/paths/path.txt");
        File file = pathZ.toFile();
        System.out.println(file);
        Path pathAgain = file.toPath();
        System.out.println(pathAgain);

        /*
         * ������������ ������� ������ � ��������� ���� Path:
         */
        Path dir = Paths.get("path/paths");
        Files.createDirectories(dir);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);
        System.out.println("����/���������� ����������?: " + Files.exists(path));
        System.out.println("��� ����������?: " + Files.isDirectory(path));
        System.out.println("��� ����?: " + Files.isRegularFile(path));
        System.out.println("��� �����: " + path.getFileName());
        System.out.println("���� � ����� ����������?: " + path.isAbsolute());
        System.out.println("������������ ���������� �����: " + path.getParent());
        System.out.println("���������� ���� � �����: " + path.toAbsolutePath());
        System.out.println("���������� ���� � ����������: " + dir.toAbsolutePath());
        System.out.println("�������� ��� ������?: " + Files.isReadable(path));
        System.out.println("�������� ��� ������?: " + Files.isWritable(path));

        /*
         * ����������� �����. ������� � ������ JDK 7 ��������� ����������� ����������� ���� � ������� ������ move().
         */
        Path dirQ = Paths.get("path/paths");
        Files.createDirectories(dirQ);
        Path pathQ = Path.of("path/paths/path.txt");
        Files.createFile(pathQ);
        Files.move(pathQ, Path.of("path/path.txt"));

        /*
         * �������� �����
         */
        Path dirE = Paths.get("path/paths");
        Files.createDirectories(dirE);
        Path pathE = Path.of("path/paths/path.txt");
        Files.createFile(pathE);
        Files.delete(dirE);

        /*
        *� ������� ����� �� ��������� ������ list() � listFiles() ������ File. ������ ���� ������� � NIO API -
        *  ��� ����� Files.newDirectoryStream()
        * ����� newDirectoryStream() ���������� �����, ���������� ����� � ����������, ����������� � ����������
        * path (��� ���������)
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
