package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class AttributesUsage {
    public static void main(String[] args) throws IOException {
        /*
        *создаётся файл в корне проекта, создается представление из атрибутов этого файла и получаются сами атрибуты:
         */
        Path file1 = Path.of("Attributes.txt");
        Files.createFile(file1);
        BasicFileAttributeView attrView = Files.getFileAttributeView(file1, BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();

        /*
        * Некоторые методы работы с атрибутами файла в целях упрощения работы с ними вынесены в класс Files и объявлены
        * статическими. Например, чтобы получить атрибуты файла, не обязательно получать представление требуемой группы
        * атрибутов, можно использовать более быстрый вариант с помощью вызова статического метода Files.readAttributes():
         */
        Path file = Path.of("Attributes.txt");
        Files.createFile(file);
        BasicFileAttributes attributes1 = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("Это обычный файл? " + attributes.isRegularFile());
        System.out.println("Это директория? " + attributes.isDirectory());
        System.out.println("Это символическая ссылка? " + attributes.isSymbolicLink());
        System.out.println("Это не файл, директория или символическая ссылка? " + attributes.isOther());
        System.out.println("Дата создания файла: " + attributes.creationTime());
        System.out.println("Размер файла: " + attributes.size());
        System.out.println("Время последнего доступа: " + attributes.lastAccessTime());
        System.out.println("Время последнего изменения: " + attributes.lastModifiedTime());
    }
}