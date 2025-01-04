package ru.job4j.io;

import java.io.File;
import java.io.IOException;

public class Dir {

    public static void main(String[] args) throws IOException {
        File file = new File("/home/sergei/projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Директория не существует: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Это не директория: %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("Размер директории: %s", file.getTotalSpace()));
        for (File subFile : file.listFiles()) {
            System.out.println(String.format("Размер файлв %s: %d", subFile.getName(), file.length()));
        }
    }
}
