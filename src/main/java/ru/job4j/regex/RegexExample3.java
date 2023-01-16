package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample3 {
    public static void main(String[] args) {
        /*
        *Если регистр букв при поиске не принципиален, можно вместе с текстом шаблона передать в метод compile()
        * параметр в виде статической константы Pattern.CASE_INSENSITIVE, определенной в классе Pattern.
        *  Теперь при поиске совпадений регистр букв учитываться не будет:
        */
        Pattern pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        String text1 = "Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "joB4J";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);
    }
}