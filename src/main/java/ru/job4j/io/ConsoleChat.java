package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botanswers) {
        this.path = path;
        this.botAnswers = botanswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> chatLog = new ArrayList<>();
        boolean isStopped = false;
        Random random = new Random();
        try (BufferedReader readUser = new BufferedReader(new InputStreamReader(System.in))) {
            String line = readUser.readLine();
            while (!OUT.equals(line)) {
                chatLog.add(line);
                if (STOP.equals(line)) {
                    isStopped = true;
                }
                if (CONTINUE.equals(line)) {
                    isStopped = false;
                }
                if (!isStopped) {
                    String botAnswer = phrases.get(random.nextInt(phrases.size()));
                    System.out.println(botAnswer);
                    chatLog.add(botAnswer);
                }
                line = readUser.readLine();
            }
            chatLog.add(line);
            saveLog(chatLog);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader readFile = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            result = readFile.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8)))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/chatLog.txt", "./data/botAnswers.txt");
        consoleChat.run();
    }
}
