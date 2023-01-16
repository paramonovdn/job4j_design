package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample3 {
    public static void main(String[] args) {
        /*
        *���� ������� ���� ��� ������ �� ������������, ����� ������ � ������� ������� �������� � ����� compile()
        * �������� � ���� ����������� ��������� Pattern.CASE_INSENSITIVE, ������������ � ������ Pattern.
        *  ������ ��� ������ ���������� ������� ���� ����������� �� �����:
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