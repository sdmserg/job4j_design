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
            this.properties = properties;
            initConnection();
    }

    private void initConnection()  {
        String url = properties.getProperty("url");
        String login = properties.getProperty("username");
        String password = properties.getProperty("password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            System.out.println("Error initializing connection: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (%s);",
                    tableName,
                    "id SERIAL PRIMARY KEY"
            );
            executeAlterTableQuery(sql);
    }

    public void dropTable(String tableName) {
            String sql = String.format(
                    "DROP TABLE IF EXISTS %s;",
                    tableName
            );
             executeAlterTableQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
            String sql = String.format(
                    "ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName, columnName, type
            );
            executeAlterTableQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) {
            String sql = String.format(
                    "ALTER TABLE %s DROP COLUMN %s;",
                    tableName, columnName
            );
            executeAlterTableQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
            String sql = String.format(
                    "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName, columnName, newColumnName
            );
            executeAlterTableQuery(sql);
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

    private void executeAlterTableQuery(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error executing query " + e.getMessage());
            e.printStackTrace();
        }
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