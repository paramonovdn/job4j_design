package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample4 {
    public static void main(String[] args) {
        /*
        *����� ��������� ������ �� ��� ����������� � ������.
        * �������� ����������� � ������� ������ find() ������ Matcher.
         */
        Pattern pattern = Pattern.compile("Job4j");
        String text = "� ����� �� ����� Job4j";
        Matcher matcher = pattern.matcher(text);
        boolean isPresent = matcher.find();
        System.out.println(isPresent);
    }
}