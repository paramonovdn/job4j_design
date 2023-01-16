package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRegexExample2 {
    public static void main(String[] args) {
        /*
        *���������� ������ ��� ������ ����
        * � ������ ������ ���������� \d ����������� � \\d, ��� ��� �������� ���� "\" ��������������
        * ������������ ��� ����������. ����� �� ������ �������: ����� ���� ���� - ���� ������ ���������
        *  ������� � ��������������� "2 ����". ����� ������� ����� ���������� 2 ���� ������.
         */
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("������� ����������: " + matcher.group());
        }
    }
}