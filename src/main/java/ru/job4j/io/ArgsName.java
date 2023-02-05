package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("The key is not recognized. Check the key."));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        argValidation(args);
        for (String arg: args) {
            int simbolPosition = arg.indexOf("=");
            String key = arg.substring(1, simbolPosition);
            String value = arg.substring(simbolPosition + 1, arg.length());
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static void argValidation(String[] arguments) {
        if (arguments.length == 0) {
            throw new IllegalArgumentException(String.format("No arguments passed"));
        }
        for (int i = 0; i < arguments.length; i++) {
            if (!arguments[i].contains("=")) {
                throw new IllegalArgumentException(String.format("In pair number %s missing separator \"=\"."
                        + " Invalid pair- %s.", i + 1, arguments[i]));
            } else {
                int simbolPosition = arguments[i].indexOf("=");
                String key = arguments[i].substring(0, simbolPosition);
                String value = arguments[i].substring(simbolPosition + 1, arguments[i].length());
                if (!key.startsWith("-")) {
                    throw new IllegalArgumentException(String.format("The key must start with \"-\"."
                            + " Invalid pair: %s", arguments[i]));
                }
                key = arguments[i].substring(1, simbolPosition);
                if (key.isEmpty() || value.isEmpty()) {
                    throw new IllegalArgumentException(String.format("Key or values in pair number- %s is empty,"
                            + " Invalid pair: %s", i + 1, arguments[i]));
                }
            }
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}