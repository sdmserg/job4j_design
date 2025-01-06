package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: '%s' is missing", key)
            );
        }
        return values.get(key);
    }

    public void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String arg : args) {
            validateArg(arg);
            int indexSignEq = arg.indexOf("=");
            String key = arg.substring(1, indexSignEq);
            String value = arg.substring(indexSignEq + 1, arg.length());
            values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public void validateArg(String arg) {
        if (!arg.startsWith("-")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not start with a '-' character", arg)
            );
        }
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain an equal sign", arg)
            );
        }
        int indexSignEq = arg.indexOf("=");
        if (indexSignEq == (arg.length() - 1)) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain a value", arg)
            );
        }
        String leftSubstring = arg.substring(0, indexSignEq);
        if ("-".equals(leftSubstring)) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain a key", arg)
            );
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
