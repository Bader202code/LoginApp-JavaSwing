package org.example;

import java.sql.*;

public class MyJDBC {

    // Register new user to the database
    // true-register success
    // false-register failure
    public static boolean register(String username, String password) {
        // first check if the username already exists in the database

        return false;
    }

    // check if the username already exists in the database
    // true-register success
    // false-register failure
    public static boolean checkUser(String username) {
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.DB_URL,
                    CommonConstants.DB_USERNAME,CommonConstants.DB_PASSWORD);

            PreparedStatement checkUserExists = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.DB_USERS_TABLE_NAME +
                    "WHERE USERNAME = ?"
            );
            checkUserExists.setString(1,username);

            ResultSet resultSet = checkUserExists.executeQuery();


            // check to see if the result set is empty
            // if it is empty it means that there was no data row that contains the username
            //(i.e user does not exist)
            if(!resultSet.isBeforeFirst()){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
