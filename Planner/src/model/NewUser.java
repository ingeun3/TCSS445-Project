package model;

import java.sql.*;

/**
 * This class create information for New User.
 * @author Ingeun Hwang, Khin Win
 */
public class NewUser {

    // initialize the myOutput.
    private int myOutput;

    /**
     * This is the constructor for NewUser.
     * @param theUsername  the username.
     * @param thePassword  the password.
     * @throws ClassNotFoundException
     */
    public NewUser(final String theUsername, final String thePassword) {
        createUser(theUsername, thePassword);
    }

    /**
     * This method create the information for new user.
     * @param theUsername  username.
     * @param thePassword  password.
     * @throws ClassNotFoundException
     */
    private void createUser(final String theUsername, final String thePassword) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
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

    /**
     * This method return myOutput.
     * @return  myOutput.
     */
    public int getMyOutput() {
        return myOutput;
    }
}
