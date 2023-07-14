package model;

import java.sql.*;
import java.util.ArrayList;

public class Event {
    private String myUsername;
    private ArrayList<String> myEventTitles = new ArrayList<String>();
    private ArrayList<Integer> myEventPrio = new ArrayList<Integer>();
    private ArrayList<String> myEvent = new ArrayList<String>();
    private ArrayList<String> myEventDueDate = new ArrayList<String>();

    public Event(String theUsername) {
        myUsername = theUsername;
    }

    public void createEvent(String theTitle, int thePrio, String theDue, String theDescription) {
        insertEvent(theTitle);
        insertAssignmentDetail(theTitle, thePrio, theDue, theDescription);
    }

    public ArrayList<String> getTitle() {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            String query = "SELECT title FROM " + ServerData.EVENT_TABLE + " WHERE username = " + myUsername;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                myEventTitles.add(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myEventTitles;
    }

//    public int getPrio(String theTitle){
//        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
//            Statement statement = connection.createStatement();
//            String query = "SELECT priority FROM " + ServerData.EVENT_TABLE + " WHERE title = " + myUsername + "AND title = " + theTitle;
//            ResultSet resultSet = statement.executeQuery(query);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return myEventPrio;
//    }

//    public String getDescription(){
//        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
//            Statement statement = connection.createStatement();
//            String query = "SELECT title FROM " + ServerData.EVENT_TABLE + " WHERE username = " + myUsername;
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()) {
//                String title = resultSet.getString("title");
//                myEventTitles.add(title);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return myEventTitles;
//    }
    private void insertEvent(String theTitle) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO" + ServerData.EVENT_TABLE +"(username, title) VALUES (?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, myUsername);
            insertStatement.setString(2, theTitle);
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

    private void insertAssignmentDetail( String theTitle, int thePrio, String theDue, String theDescription) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String insertQuery = "INSERT INTO" + ServerData.ASSIGNMENT_DETAIL_TABLE +"(title, priority, due, description) VALUES (?,?,?,?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, theTitle);
            insertStatement.setInt(2, thePrio);
            insertStatement.setString(3, theDue);
            insertStatement.setString(4, theDescription);
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
