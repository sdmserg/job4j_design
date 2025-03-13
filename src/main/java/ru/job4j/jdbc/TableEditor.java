package ru.job4j.jdbc;

import java.io.InputStream;

import java.util.Properties;
import java.util.StringJoiner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        try {
            this.properties = properties;
            initConnection();
        } catch (SQLException e) {
            System.out.println("Error initializing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initConnection() throws SQLException {
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (%s);",
                    tableName,
                    "id SERIAL PRIMARY KEY"
            );
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;",
                    tableName
            );
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error drop table " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName, columnName, type
            );
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error add column  " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName, columnName
            );
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error drop column  " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName, columnName, newColumnName
            );
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Error rename column  " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream input = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(input);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable("demo");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.addColumn("demo", "name", "VARCHAR(20)");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.renameColumn("demo", "name", "username");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.dropColumn("demo", "username");
            System.out.println(tableEditor.getTableScheme("demo"));
            tableEditor.dropTable("demo");
        }
    }
}