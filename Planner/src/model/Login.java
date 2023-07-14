package model;

import java.sql.*;

// User -> if username not exist, insert, else dont make change
// event -> if username and password match, if not say "username or password not exist"
public class Login {
    private boolean myStatus;

    public Login(String theUsername, String thePassword) {
        signingIn(theUsername,thePassword);
    }
    public void signingIn(String theUsername, String thePassword){
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            // Assuming the table name is "users" and the username column name is "username"

            // Check if the username already exists
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?, password = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, theUsername);
            checkStatement.setString(2, thePassword);
            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();


            int count = checkResultSet.getInt(1);

            if (count > 0) {
                myStatus = true;
            } else {
                System.out.println("Username or password not existing");
                return;
            }

            checkResultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // This is going to get called by the controller class and the event class

    public boolean isLoginSuccessful() {
        return myStatus;
    }

}
