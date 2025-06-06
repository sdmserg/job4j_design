package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            var computerMove = getMove(startAt);
            System.out.println(computerMove);
            startAt++;
            var answer = input.nextLine();
            var expectedAnswer = getMove(startAt);
            if (!expectedAnswer.equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            } else {
                System.out.println(expectedAnswer);
            }
            startAt++;
        }
    }

    public static String getMove(int num) {
        var result = String.valueOf(num);
        if (num % 15 == 0) {
            result = "FizzBuzz";
        } else if (num % 3 == 0) {
            result = "Fizz";
        } else if (num % 5 == 0) {
            result = "Buzz";
        }
        return result;
    }
}
