package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRegexExample4 {
    public static void main(String[] args) {
        /*
        *нужно найти адреса электронной почты в тексте. Запись адреса электронной почты может выглядеть примерно так:
        *  peter-2022@example.com. По аналогии с предыдущим примером, разобьем данную запись на простейшие части:
        * любой символ от 1 раза, знак собаки, любой символ от 1 раза, точка, любой символ от 1 раза.
         */
        Pattern pattern = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }
}