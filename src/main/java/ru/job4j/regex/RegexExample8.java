package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample8 {
    public static void main(String[] args) {
        /*
          *��������� ���������� ����� �������� ������ ������� � ������� ������ replaceAll(),
          * ������� ����������� � ������������� matcher.
        */
        Pattern pattern = Pattern.compile("123");
        String text = "1231 � 1232 � 1233";
        Matcher matcher = pattern.matcher(text);
        String rsl = matcher.replaceAll("Job4j");
        System.out.println(rsl);
    }
}