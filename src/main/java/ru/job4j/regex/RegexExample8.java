package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample8 {
    public static void main(String[] args) {
        /*
          *Найденные совпадения можно заменить другой строкой с помощью метода replaceAll(),
          * который применяется к сопоставителю matcher.
        */
        Pattern pattern = Pattern.compile("123");
        String text = "1231 и 1232 и 1233";
        Matcher matcher = pattern.matcher(text);
        String rsl = matcher.replaceAll("Job4j");
        System.out.println(rsl);
    }
}