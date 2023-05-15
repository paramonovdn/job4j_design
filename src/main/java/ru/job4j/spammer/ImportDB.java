package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private Connection getConnection() throws Exception {
        String driver = cfg.getProperty("jdbc.driver");
        String url = cfg.getProperty("jdbc.url");
        String login = cfg.getProperty("jdbc.username");
        String password = cfg.getProperty("jdbc.password");
        Class.forName(driver);
        return DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) throws Exception {
        try (Statement statement = getConnection().createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS " + tableName + " (id SERIAL PRIMARY KEY, name TEXT, email TEXT)"
            );
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            for (String line = rd.readLine(); line != null; line = rd.readLine()) {
                if (line.startsWith(";")) {
                    line = line.substring(1, line.length());
                }
                if (line.endsWith(";")) {
                    line = line.substring(0, line.length() - 1);
                }
                if (!line.contains(";")) {
                    throw new IllegalArgumentException();
                } else {
                    int simbolPosition = line.indexOf(";");
                    String name = line.substring(0, simbolPosition);
                    String email = line.substring(simbolPosition + 1, line.length());
                    if (name.isEmpty() || email.isEmpty()) {
                        throw new IllegalArgumentException();
                    } else {
                        users.add(new User(name, email));
                    }
                }
            }
        }
        return users;
    }

    public void save(List<User> users) throws Exception {
        for (User user : users) {
            try (PreparedStatement ps = getConnection().prepareStatement("INSERT INTO spammer(name, email) VALUES (?, ?)")) {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.execute();
            }
        }
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
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app2.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "C:\\projects\\job4j_design\\src\\main\\resources\\dump.txt");
        db.createTable("spammer");
        db.save(db.load());
    }
}
