package com.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://172.17.0.2:3306/JEE_Project";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "ham147532@";

    // JDBC variables for opening, closing, and managing connection
    private static Connection connection;


    public static Connection openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
        Connection conn = MySqlConnection.openConnection();
        if (conn != null) {
            System.out.println("Connected to the database!");
            MySqlConnection.closeConnection();
            System.out.println("Connection closed.");
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
