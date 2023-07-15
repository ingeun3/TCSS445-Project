package model;

import java.sql.*;

public class NewUser {

    public NewUser(String theUsername, String thePassword) {
        createUser(theUsername, thePassword);
    }

    public void createUser(String theUsername, String thePassword) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            // Assuming the table name is "users" and the username column name is "username"

            // Check if the username already exists
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, theUsername);
            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();


            int count = checkResultSet.getInt(1);

            if (count > 0) {
                System.out.println("The username already exists.");
                return; // Stop the execution or perform appropriate action
            }


            // Insert the new user account
            String insertQuery = "INSERT INTO "+ ServerData.USER_TABLE +" (username, password) VALUES (?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, theUsername);
            insertStatement.setString(2, thePassword);
            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User account created successfully!");
            } else {
                System.out.println("Failed to create user account.");
            }

            insertStatement.close();
            checkResultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
