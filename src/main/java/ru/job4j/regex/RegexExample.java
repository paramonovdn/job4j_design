package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        /*
        * �������� �� ������ ������������ ������� ������ ����� � ������� ������ matches() ������ Matcher:
         */
        Pattern pattern = Pattern.compile("� ����� �� Job4j");

        String text1 = "� ����� �� Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "� ����� �� ����� Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);



    }
}