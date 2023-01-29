package ru.job4j.generic;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainApp {


    public static void main(String[] args) {
        final Map<String, String> values = new HashMap<String, String>();
        String[] couple = new String[2];

        try (BufferedReader read = new BufferedReader(new FileReader("data/pair_with_double_equally_simbol.properties"))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (line.contains("#") || line.isEmpty()) {
                    continue;
                }
                if (line.contains("=")) {
                    couple = line.split("=");
                    values.put(couple[0], couple[1]);
                  /*  if (couple.length == 2) {
                        values.put(couple[0], couple[1]);
                    } else {
                        throw new IllegalArgumentException();
                    }*/
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* System.out.println(couple.length);
        for (int i = 0; i<= couple.length; i++) {
            System.out.println("i= " + i + ", couple[" + i + "]= " + couple[i]);
        }*/

        for (Map.Entry<String, String> entry : values.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

    }
}
