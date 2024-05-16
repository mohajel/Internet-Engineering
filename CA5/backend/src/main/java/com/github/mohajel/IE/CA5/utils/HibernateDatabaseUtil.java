package com.github.mohajel.IE.CA5.utils;

import java.sql.*;

public class HibernateDatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306";
    private static final String DATABASE_NAME = "Mizdooni";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";

    public static void createDataBase() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {

            // Check if the database exists
            String showDatabasesQuery = "SHOW DATABASES";
            ResultSet resultSet = statement.executeQuery(showDatabasesQuery);

            boolean databaseExists = false;
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (DATABASE_NAME.equals(databaseName)) {
                    databaseExists = true;
                    break;
                }
            }

            if (!databaseExists) {
                // Database does not exist, create it
                String createDatabaseQuery = "CREATE DATABASE " + DATABASE_NAME;
                statement.executeUpdate(createDatabaseQuery);
                System.out.println("Database " + DATABASE_NAME + " created successfully.");
            } else {
                System.out.println("Database " + DATABASE_NAME + " already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
