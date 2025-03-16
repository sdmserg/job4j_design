package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties config;
    private String dump;

    public ImportDB(Properties config, String dump) {
        this.config = config;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(dump))) {
            input.lines()
                    .filter(string -> !string.startsWith("#"))
                    .filter(string -> !string.isBlank())
                    .map(this::checkAndParseLine)
                    .map(array -> new User(array[0], array[1]))
                    .forEach(users::add);
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(config.getProperty("jdbc.driver"));
        try (Connection connection = DriverManager.getConnection(
                config.getProperty("jdbc.url"),
                config.getProperty("jdbc.username"),
                config.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO users (name, email) VALUES (?, ?)"
                )) {
                    preparedStatement.setString(1, user.name);
                    preparedStatement.setString(2, user.email);
                    preparedStatement.execute();
                }
            }
        }
    }

    private String[] checkAndParseLine(String line) {
        if (!line.contains(";")) {
            throw new IllegalArgumentException("Не верный формат. В строке пропущен знак ;");
        }
        String[] array = line.split(";");
        if (array.length != 2 || array[0].isBlank() || array[1].isBlank()) {
            throw new IllegalArgumentException(
                    "Не верный формат. В строке отсутсвует имя клиента или email");
        }
        return array;
    }

    private static class User {

        String name;

        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        ImportDB dateBase = new ImportDB(config, "./dump.txt");
        dateBase.save(dateBase.load());
    }
}
