package org.example;

import javax.swing.*;
import java.sql.*;

public class MyJDBC {

    // Create a reusable database connection method using DriverManager
    private static Connection getDatabaseConnection() {
        try {
            // Load MySQL JDBC driver (optional for some setups, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection using DriverManager
            Connection connection = DriverManager.getConnection(
                    CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            JOptionPane.showMessageDialog(null, "Connected to database successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            return connection;

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    // Check if a user exists in the database
    public static boolean checkUser(String username) {
        String query = "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME + " WHERE USERNAME = ?";

        try (Connection connection = getDatabaseConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.isBeforeFirst(); // Returns true if the user exists
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Register a new user in the database
    public static boolean register(String username, String password) {

        try{
            // first check if the username already exists in the database
            if(!checkUser(username)) {
                // Connection to the database
                Connection connection = DriverManager.getConnection(
                        CommonConstants.DB_URL,
                        CommonConstants.DB_USERNAME,
                        CommonConstants.DB_PASSWORD);

                // Creating input query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.DB_USERS_TABLE_NAME + "(username, password)" +
                                "VALUES(?, ?)"
                );

                // Insert parameters in the insert query
                insertUser.setString(1,username);
                insertUser.setString(2,password);

                // Update database with new user
                insertUser.executeUpdate();
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    // validate login credentials by checking to see if username/password pair exists in the database
    public static boolean validateLogin(String username, String password){

        try {

            Connection connection = DriverManager.getConnection(
                    CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,
                    CommonConstants.DB_PASSWORD);

            // Creating select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                            " WHERE USERNAME = ? AND PASSWORD = ?"
            );
            validateUser.setString(1,username);
            validateUser.setString(2,password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }
}
