package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/JEE_Project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "19853";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;

    // Open a connection to the database
    public static Connection openConnection() {
        try {
            // Register the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Close the connection to the database
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example of using the MySqlConnection class
        Connection conn = MySqlConnection.openConnection();
        if (conn != null) {
            System.out.println("Connected to the database!");

            // Perform your database operations here

            MySqlConnection.closeConnection();
            System.out.println("Connection closed.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
