package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader input = new BufferedReader(
                new FileReader(path)
        )) {
            input.lines()
                    .filter(string -> !string.startsWith("#"))
                    .filter(string -> !string.isBlank())
                    .map(this::checkAndParseLine)
                    .forEach(array -> values.put(array[0].trim(), array[1].trim()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader input = new BufferedReader(
                new FileReader(path)
        )) {
            input.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    private String[] checkAndParseLine(String line) {
        if (!line.contains("=")) {
            throw new IllegalArgumentException(
                    "Не верный формат. В строках конфига пропущен знак =");
        }
        String[] array = line.split("=", 2);
        if (array.length != 2 || array[0].isBlank() || array[1].isBlank()) {
            throw new IllegalArgumentException(
                    "Не верный формат. В строках конфига отсутсвует ключ или значение");
        }
        return array;
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
