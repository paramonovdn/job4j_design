package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRegexExample3 {
    public static void main(String[] args) {
        /*
        *Ўаблон поиска даты с ограничением, чтобы дата была цельным литералом (отдельным словом)
        */
        Pattern pattern = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Ќайдено совпадение: " + matcher.group());
        }
    }
}