package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample4 {
    public static void main(String[] args) {
        /*
        *можно проверить шаблон на его присутствие в тексте.
        * Проверка выполняется с помощью метода find() класса Matcher.
         */
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Я учусь на курсе Job4j";
        Matcher matcher = pattern.matcher(text);
        boolean isPresent = matcher.find();
        System.out.println(isPresent);
    }
}