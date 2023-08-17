package model;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class create Event for each table.
 *
 * @author Khin Win, Ingeun Hwang
 */
public class CreateEvent {

    // initialized myUsername.
    private String myUsername;

    // initialized myAssignmentID.
    private int myAssignmentID;

    /**
     * This is the constructor for create Event.
     * @param theUsername  username.
     * @param theTitle     title of the assignment.
     * @param theDate      due_date.
     * @param theProfFName  professor First Name.
     * @param theProfLName  professor Last Name.
     * @param thePrio       priority of the assignment.
     */
    public CreateEvent(String theUsername, String theTitle, Date theDate, String theProfFName, String theProfLName, int thePrio)   {
        myUsername = theUsername;
        int lastUsedAssignmentID = new SQLQueries().getLastUsedAssignmentID();
        // Increment the assignment ID for the new instance
        myAssignmentID = lastUsedAssignmentID + 1;
        insertEvent(theTitle);
        insertAssignmentDetail(thePrio, theDate);
        insertProfessor(theProfFName,theProfLName);
        insertTime();
    }

    /**
     * This method create the Event_table.
     * @param theTitle the title of the assignment.
     */

    private void insertEvent(String theTitle) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO " + ServerData.EVENT_TABLE +"(username, title, assignment_id ) VALUES (?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, myUsername);
            insertStatement.setString(2, theTitle);
            insertStatement.setInt(3, myAssignmentID);
            insertStatement.executeUpdate();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method create the Assignment Detail Table.
     * @param thePrio  the Prority of the Assignment.
     * @param theDate  the Due Date of the assignment.
     */
    private void insertAssignmentDetail(int thePrio, Date theDate) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO " + ServerData.ASSIGNMENT_DETAIL_TABLE +"(assignment_id, prio, due_date ) VALUES (?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, myAssignmentID);
            insertStatement.setInt(2, thePrio);
            insertStatement.setDate(3, theDate);
            insertStatement.executeUpdate();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * this method create the Professor Table.
     * @param theProfessorFName  the Professor First Name.
     * @param theProfessorLName  the Professor Last Name.
     */
    private void insertProfessor(String theProfessorFName, String theProfessorLName) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME,
                ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO " + ServerData.PROF_TABLE +
                    "(assignment_id, Fname, Lname ) VALUES (?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, myAssignmentID);
            insertStatement.setString(2,  theProfessorFName);
            insertStatement.setString(3,  theProfessorLName);
            insertStatement.executeUpdate();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method create the Time Table.
     */
    private void insertTime() {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO " + ServerData.TIME_TABLE +"(assignment_id) VALUES (?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, myAssignmentID);
            insertStatement.executeUpdate();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
