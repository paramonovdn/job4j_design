package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRegexExample {
    public static void main(String[] args) {
        /*
        *String.split()
        *����� ���������� ��������� ����� ������������ � ���� ����������� � ������ split() ������ String.
        *� ������� ���� �� ������, ��������� �� ���� � ��������, ����������� ������ ������������������ ����,
        * � ������� ����������� � ������� ����������� ���������.
        * ���������� ��������� "\\D+" ������ "����� ���������� ������ �� ������ ����".
         */
        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+");
        System.out.println(Arrays.toString(rsl));
    }
}