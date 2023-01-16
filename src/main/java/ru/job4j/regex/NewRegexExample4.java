package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRegexExample4 {
    public static void main(String[] args) {
        /*
        *����� ����� ������ ����������� ����� � ������. ������ ������ ����������� ����� ����� ��������� �������� ���:
        *  peter-2022@example.com. �� �������� � ���������� ��������, �������� ������ ������ �� ���������� �����:
        * ����� ������ �� 1 ����, ���� ������, ����� ������ �� 1 ����, �����, ����� ������ �� 1 ����.
         */
        Pattern pattern = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("������� ����������: " + matcher.group());
        }
    }
}