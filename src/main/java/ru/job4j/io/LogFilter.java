package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LogFilter {
    public static  List<String> filter(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                if (line.contains(" 404 ")) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(file))) {
            for (String l : log) {
                out.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}