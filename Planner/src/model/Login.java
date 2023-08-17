package model;

import java.sql.*;

/**
 * This class is has the SQL query to check if the login credential exists.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class Login {
    /** true if the account exist, false otherwise. */
    private boolean myDoesNotExist;
    /**
     * Default constructor for login object
     * @param theUsername the username to check if exist in database.
     * @param thePassword the password to check if exist along with username in the database.
     * @throws ClassNotFoundException throws exception if the class not found
     */
    public Login(final String theUsername, final String thePassword) throws ClassNotFoundException {
        myDoesNotExist = false;
        signingIn(theUsername,thePassword);
    }
    /**
     * Checks if the username and password exist in database
     * @param theUsername the username to check if exist in database.
     * @param thePassword the password to check if exist along with username in the database.
     */
    public void signingIn(final String theUsername, final String thePassword){
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {

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
    /**
     * Getter for myLoginExist
     * @return myLoginExist
     */
    public boolean getMyOutput() {
        return myDoesNotExist;
    }

}
