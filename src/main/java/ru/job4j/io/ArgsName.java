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
        for (String arg: args) {
            int symbolPosition = arg.indexOf("=");
            argValidation(arg, symbolPosition);
            String key = arg.substring(1, symbolPosition);
            String value = arg.substring(symbolPosition + 1, arg.length());
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException(String.format("No arguments passed."));
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static void argValidation(String arg, int symbolPosition) {
            if (!arg.contains("=")) {
                throw new IllegalArgumentException(String.format("In pair missing separator \"=\"."
                        + " Invalid pair: %s.", arg));
            } else {
                String key = arg.substring(0, symbolPosition);
                String value = arg.substring(symbolPosition + 1, arg.length());
                if (!key.startsWith("-")) {
                    throw new IllegalArgumentException(String.format("The key must start with \"-\"."
                            + " Invalid pair: %s", arg));
                }
                key = arg.substring(1, symbolPosition);
                if (key.isEmpty() || value.isEmpty()) {
                    throw new IllegalArgumentException(String.format("Key or values in pair is empty."
                            + " Invalid pair: %s", arg));
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