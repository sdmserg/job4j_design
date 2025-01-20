package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        long number = 161524312434L;
        int age = 33;
        double salary = 120000.50;
        float experience = 2.5f;
        boolean isDeveloper = true;
        char firstLetter = 'P';
        short officeNumber = 650;
        byte skillLevel = 100;
        LOG.debug("User info name: {}, age: {}", name, age);
        LOG.info("User name: {}, number: {}, age: {}, salary: {}, "
                        + "experience: {}, isDeveloper: {}, "
                        + "first character of name: {}, office number: {}, "
                        + "level of skill: {}",
                name, number, age, salary,
                experience, isDeveloper,
                firstLetter, officeNumber,
                skillLevel);
    }
}
