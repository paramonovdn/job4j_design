package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class AttributesUsage {
    public static void main(String[] args) throws IOException {
        /*
        *�������� ���� � ����� �������, ��������� ������������� �� ��������� ����� ����� � ���������� ���� ��������:
         */
        Path file = Path.of("Attributes.txt");
        Files.createFile(file);
        BasicFileAttributeView attrView = Files.getFileAttributeView(file, BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();

        /*
        * ��������� ������ ������ � ���������� ����� � ����� ��������� ������ � ���� �������� � ����� Files � ���������
        * ������������. ��������, ����� �������� �������� �����, �� ����������� �������� ������������� ��������� ������
        * ���������, ����� ������������ ����� ������� ������� � ������� ������ ������������ ������ Files.readAttributes():
         */
        /*
      *  Path file = Path.of("Attributes.txt");
      *  Files.createFile(file);
         */
        BasicFileAttributes attributes1 = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("��� ������� ����? " + attributes.isRegularFile());
        System.out.println("��� ����������? " + attributes.isDirectory());
        System.out.println("��� ������������� ������? " + attributes.isSymbolicLink());
        System.out.println("��� �� ����, ���������� ��� ������������� ������? " + attributes.isOther());
        System.out.println("���� �������� �����: " + attributes.creationTime());
        System.out.println("������ �����: " + attributes.size());
        System.out.println("����� ���������� �������: " + attributes.lastAccessTime());
        System.out.println("����� ���������� ���������: " + attributes.lastModifiedTime());
    }
}