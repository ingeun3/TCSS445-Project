package model;

import java.sql.*;

// User -> if username not exist, insert, else dont make change
// event -> if username and password match, if not say "username or password not exist"
public class Login {
    private boolean myDoesNotExist;

    public Login(final String theUsername, final String thePassword) throws ClassNotFoundException {
        myDoesNotExist = false;
        signingIn(theUsername,thePassword);
    }
    public void signingIn(final String theUsername, final String thePassword){
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            // Assuming the table name is "users" and the username column name is "username"

            // Check if the username already exists
            String checkQuery = "SELECT COUNT(*) FROM user_table WHERE username = ? AND password = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, theUsername);
            checkStatement.setString(2, thePassword);


            ResultSet checkResultSet = checkStatement.executeQuery();
            checkResultSet.next();


            int count = checkResultSet.getInt(1);

            if (count == 0) {
                myDoesNotExist = true;
                return;
            }

            checkResultSet.close();
            checkStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // This is going to get called by the controller class and the event class

    public boolean getMyOutput() {
        return myDoesNotExist;
    }

}
