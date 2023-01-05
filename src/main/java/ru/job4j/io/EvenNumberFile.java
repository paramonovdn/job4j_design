package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = null;
        try (FileInputStream in = new FileInputStream("even.txt")) {
            text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                boolean rsl = Integer.parseInt(line) % 2 == 0;
                if (rsl) {
                    System.out.println(line + " -четное число");
                } else {
                    System.out.println(line + " -нечетное число");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}