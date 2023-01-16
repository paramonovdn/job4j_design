package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewRegexExample {
    public static void main(String[] args) {
        /*
        *String.split()
        *“акже регул€рные выражени€ можно использовать в виде разделител€ в методе split() класса String.
        *¬ примере ниже из строки, состо€щей из цифр и символов, вычлен€ютс€ только последовательности цифр,
        * а символы отсеиваютс€ с помощью регул€рного выражени€.
        * –егул€рное выражение "\\D+" значит "любой нецифровой символ от одного раза".
         */
        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+");
        System.out.println(Arrays.toString(rsl));
    }
}