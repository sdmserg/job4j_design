package ru.job4j.serialization.json;

import java.util.Arrays;

public class ChessPlayer {

    private String name;

    private int age;

    private boolean isActive;

    private Contact contact;

    private String[] formats;

    public ChessPlayer() {

    }

    public ChessPlayer(String name, int age, boolean isActive,
                       Contact contact, String[] formats) {
        this.name = name;
        this.age = age;
        this.isActive = isActive;
        this.contact = contact;
        this.formats = formats;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isActive() {
        return isActive;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getFormats() {
        return formats;
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
