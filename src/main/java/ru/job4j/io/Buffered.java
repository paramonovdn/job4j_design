package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Buffered {
    public static void main(String[] args) {
        /*
        * Буферное побайтовое чтение/запись реализовано классами BufferedInputStream и BufferedOutputStream.
        * Буферное посимвольное чтение/запись реализовано классами BufferedReader и BufferedWriter. Все buffered* классы
        * являются обёртками и не могут существовать сами по себе. При создании объектов этих классов в их конструкторы
        * нужно передавать объекты классов-реализаций InputStream/OutputStream. Могут быть и цепочки обёрток, но в корне
        * всегда должен быть байтовый или символьный поток.
         */
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/input.txt"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output.txt"))) {
            out.write(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        *в FileOutputStream используем другой конструктор FileOutputStream(String name, boolean append)
        * он позволит не перезаписывать информацию в файле output1.txt, а добавлять в конец файла
         */
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/input.txt"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output1.txt", true))) {
            out.write(in.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}