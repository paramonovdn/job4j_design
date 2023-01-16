package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample7 {
    public static void main(String[] args) {
        /*
         *Также существуют методы получения начального и конечного индексов найденного совпадения.
         *Метод start() - получает индекс, в котором находится первый символ искомой последовательности символов.
         *Метод end() - получает индекс, следующий за последним символов искомой последовательности символов.
        */
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j1 и Job4j2 и Job4j3";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение. iStart: " + matcher.start()
                    + " iEnd: " + matcher.end());
        }
    }
}