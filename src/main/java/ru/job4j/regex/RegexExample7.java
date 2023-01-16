package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample7 {
    public static void main(String[] args) {
        /*
         *����� ���������� ������ ��������� ���������� � ��������� �������� ���������� ����������.
         *����� start() - �������� ������, � ������� ��������� ������ ������ ������� ������������������ ��������.
         *����� end() - �������� ������, ��������� �� ��������� �������� ������� ������������������ ��������.
        */
        Pattern pattern = Pattern.compile("Job4j");
        String text = "Job4j1 � Job4j2 � Job4j3";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("������� ����������. iStart: " + matcher.start()
                    + " iEnd: " + matcher.end());
        }
    }
}