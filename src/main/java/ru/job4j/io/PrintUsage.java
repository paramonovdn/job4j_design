package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {

    public static void main(String[] args) {
        /*
        * ������������� ������ ������ PrintStream, ��������� ������ ����� FileOutputStream
        * ������������� PrintWriter ��� ������ � ��������� ����
         */
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"));
            PrintWriter writer = new PrintWriter("data/write.txt")) {
            stream.println("�� PrintStream � FileOutputStream");
            stream.write("����� ������".getBytes());
            writer.println("����� ���������");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}