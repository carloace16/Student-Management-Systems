package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseUtil {

    // Properties object to hold database connection details
    private static Properties properties = new Properties();

    // Static block to load database properties and register JDBC driver
    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("database.properties")) {
            // Load database properties from the properties file
            properties.load(input);
            // Register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            // Handle exceptions during initialization by throwing an Error
            throw new ExceptionInInitializerError(e);
        }
    }

    // Method to get a connection to the database
    public static Connection getConnection() throws SQLException {
        // Retrieve database connection details from properties
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        // Create and return a database connection using the details
        return DriverManager.getConnection(url, username, password);
    }
}
