package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample6 {
    public static void main(String[] args) {
        /*
        *ѕолучить найденное совпадение в виде строки можно с помощью метода group().
        *  Ётот метод выводит ту часть текста, котора€ совпадает с шаблоном регул€рного выражени€.
        * ¬ данном случае это "Job4j".
        */
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Ќайдено совпадение: " + matcher.group());
        }
    }
}