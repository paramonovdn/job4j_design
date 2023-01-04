package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        int result = 0;
        String s = "";
        String digit = "";
        String firstLine = "* |1 2 3 4 5 6 7 8 9 10" + "\n";
        String limiter = "_________________________" + "\n";

        for (int i = 1; i <= 10; i++){
            for (int j = 1; j <= 10; j++){
                result = i * j;
                s += String.valueOf(result) + " ";
            }
            digit += String.valueOf(i) + " |" + s + "\n";
            s = "";
            try (FileOutputStream out = new FileOutputStream("result.txt")) {
                 out.write(firstLine.getBytes());
                 out.write(limiter.getBytes());
                 out.write(digit.getBytes());
                 out.write(System.lineSeparator().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }    
        }
    }
}