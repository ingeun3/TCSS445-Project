package model;

import java.sql.*;
import java.util.ArrayList;

public class CreateEvent {
    private String myUsername;

    private int myAssignmentID;
    private ArrayList<String> myEventTitles = new ArrayList<String>();
    private ArrayList<Integer> myEventPrio = new ArrayList<Integer>();
    private ArrayList<String> myEvent = new ArrayList<String>();
    private ArrayList<String> myEventDueDate = new ArrayList<String>();

    public CreateEvent(String theUsername, String theTitle, String theProf, int thePrio, Date theDate, Time theStart, Time theEnd) {
        myUsername = theUsername;
        myAssignmentID++;

        insertEvent(theTitle);
        insertAssignmentDetail(thePrio, theDate);
        insertProfessor(theProf);
        insertTime(theStart, theEnd);

    }

    private void insertEvent(String theTitle) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO" + ServerData.EVENT_TABLE +"(username, title, assignment_id) VALUES (?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, myUsername);
            insertStatement.setString(2, theTitle);
            insertStatement.setInt(3, myAssignmentID);
            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User account created successfully!");
            } else {
                System.out.println("Failed to create user account.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAssignmentDetail(int thePrio, Date theDate) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO" + ServerData.ASSIGNMENT_DETAIL_TABLE +"(assignment_id, priority, due) VALUES (?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, myAssignmentID);
            insertStatement.setInt(2, thePrio);
            insertStatement.setDate(3, theDate);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User account created successfully!");
            } else {
                System.out.println("Failed to create user account.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertProfessor(String theProfessor) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO" + ServerData.PROF_TABLE +"(assignment_id, professor) VALUES (?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, myAssignmentID);
            insertStatement.setString(2,  theProfessor);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User account created successfully!");
            } else {
                System.out.println("Failed to create user account.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertTime(Time theStart, Time theEnd) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO" + ServerData.TIME_TABLE +"(assignment_id, start_time, end_time) VALUES (?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, myAssignmentID);
            insertStatement.setTime(2,  theStart);
            insertStatement.setTime(3, theEnd);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User account created successfully!");
            } else {
                System.out.println("Failed to create user account.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
