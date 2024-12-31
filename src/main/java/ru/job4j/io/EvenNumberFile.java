package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                int num = Integer.parseInt(number);
                if (num % 2 == 0) {
                    System.out.println("The number " + num + " is even.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
