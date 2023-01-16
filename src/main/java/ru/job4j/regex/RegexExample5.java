package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample5 {
    public static void main(String[] args) {
        /*
        * Метод find() ищет ближайшее совпадение. Его можно применять многократно.
        * Каждый вызов метода find() начинается с места, где закончился его предыдущий вызов.
        * Чтобы найти многократные вхождения шаблона в коде, нужно использовать find() в цикле:
         */
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j и Job4j и Job4j";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение");
        }
    }
}