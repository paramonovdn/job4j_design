package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    private Statement statement;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection()  {
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        try {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS " + tableName + " (id SERIAL PRIMARY KEY)"

            );
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable(String tableName) {
        try {
            String sql = String.format(
                    "DROP TABLE " + tableName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try {
            String sql = String.format(
                    "ALTER TABLE " + tableName +  " ADD COLUMN " + columnName + " " + type
            );
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try {
            String sql = String.format(
                    "ALTER TABLE "  + tableName + " DROP " + columnName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try {
            String sql = String.format(
                    "ALTER TABLE " + tableName + " RENAME COLUMN " + columnName + " TO " + newColumnName
            );
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        TableEditor tableEditor = new TableEditor(config);
        tableEditor.createTable("auto");
        tableEditor.getTableScheme("auto");
        tableEditor.addColumn("auto", "name", "TEXT");
        tableEditor.addColumn("auto", "model", "TEXT");
        tableEditor.getTableScheme("auto");
        tableEditor.renameColumn("auto", "name", "producer_name");
        tableEditor.getTableScheme("auto");
        tableEditor.dropColumn("auto", "model");
        tableEditor.getTableScheme("auto");
        tableEditor.dropTable("auto");
        tableEditor.close();

    }
}