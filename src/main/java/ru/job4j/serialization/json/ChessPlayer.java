package ru.job4j.serialization.json;

import java.util.Arrays;

public class ChessPlayer {
    private final String name;

    private final int age;

    private final boolean isActive;

    private final Contact contact;

    private final String[] formats;

    public ChessPlayer(String name, int age, boolean isActive,
                       Contact contact, String[] formats) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.contact = contact;
        this.formats = formats;
    }

    @Override
    public String toString() {
        return "ChessPlayer{"
                + "name=" + name
                + ", age=" + age
                + ", isActive=" + isActive
                + ", contact=" + contact
                + ", formats=" + Arrays.toString(formats)
                + "}";
    }
}
