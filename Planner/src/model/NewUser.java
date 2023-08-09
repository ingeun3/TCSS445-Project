package model;

import java.sql.*;

public class NewUser {
    private int myOutput;

    public NewUser(String theUsername, String thePassword) throws ClassNotFoundException {
        createUser(theUsername, thePassword);
    }

    private void createUser(String theUsername, String thePassword) throws ClassNotFoundException {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {


            // Assuming the table name is "users" and the username column name is "username"

            // Check if the username already exists
            String checkQuery = "SELECT COUNT(*) FROM user_table WHERE username = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, theUsername);
            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();


            int count = checkResultSet.getInt(1);

            if (count > 0) {
                myOutput = 1;
                return; // Stop the execution or perform appropriate action
            }


            // Insert the new user account
            String insertQuery = "INSERT INTO "+ ServerData.USER_TABLE +" (username, password) VALUES (?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, theUsername);
            insertStatement.setString(2, thePassword);
            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                myOutput = 2;
            } else {
                myOutput = 3;
            }

            insertStatement.close();
            checkResultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int getMyOutput() {
        return myOutput;
    }
}
