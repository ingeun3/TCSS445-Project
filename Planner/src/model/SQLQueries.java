package model;

import java.sql.*;
import java.util.ArrayList;

public class SQLQueries {
    private ArrayList<String> myEventTitles = new ArrayList<String>();
    private ArrayList<Object[]> res = new ArrayList<>();


    public ArrayList<String> getTitle(String theUsername) throws ClassNotFoundException {

        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT title FROM " + ServerData.EVENT_TABLE + " WHERE username = " + theUsername;
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


    public int getAssignmentID(String theUsername, String theAssignmentTitle) throws ClassNotFoundException {
        int res = -1;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT assignment_id FROM " + ServerData.EVENT_TABLE + " WHERE username = " + theUsername
                            + " and assignment_title = " + theAssignmentTitle;
            ResultSet resultSet = statement.executeQuery(query);
            res = resultSet.getInt("assignment_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getPrio(int theAssignmentID) throws ClassNotFoundException {
        int res = -1;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT priority FROM " + ServerData.ASSIGNMENT_DETAIL_TABLE + " WHERE assignment_id = " + theAssignmentID;
            ResultSet resultSet = statement.executeQuery(query);
            res = resultSet.getInt("priority");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Date getDate(int theAssignmentID) throws ClassNotFoundException {
        Date res = null;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT due FROM " + ServerData.ASSIGNMENT_DETAIL_TABLE + " WHERE assignment_id = " + theAssignmentID;
            ResultSet resultSet = statement.executeQuery(query);
            res = resultSet.getDate("due");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public String getProf(int theAssignmentID) throws ClassNotFoundException {

        String res = null;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT professor FROM " + ServerData.PROF_TABLE + " WHERE assignment_id = " + theAssignmentID;
            ResultSet resultSet = statement.executeQuery(query);
            res = resultSet.getString("professor");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Time getStart(int theAssignmentID) throws ClassNotFoundException {

        Time res = null;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT start FROM " + ServerData.TIME_TABLE + " WHERE assignment_id = " + theAssignmentID;
            ResultSet resultSet = statement.executeQuery(query);
            res = resultSet.getTime("start");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public Time getEnd(int theAssignmentID) throws ClassNotFoundException {

        Time res = null;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = connection.createStatement();
            String query = "SELECT end FROM " + ServerData.TIME_TABLE + " WHERE assignment_id = " + theAssignmentID;
            ResultSet resultSet = statement.executeQuery(query);
            res = resultSet.getTime("end");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getLastUsedAssignmentID() throws ClassNotFoundException {
        int res = -1;
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            Statement statement = connection.createStatement();
            String query = "SELECT MAX(assignment_id) id FROM " + ServerData.EVENT_TABLE;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            res = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<Object[]> getAllEventForUser(String theUsername) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT * FROM " + ServerData.USER_TABLE +
                    " NATURAL JOIN " + ServerData.EVENT_TABLE +
                    " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE +
                    " NATURAL JOIN " + ServerData.PROF_TABLE +
                    " NATURAL JOIN " + ServerData.TIME_TABLE +
                    " WHERE username = ?";

            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theUsername);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                Date dueDate = resultSet.getDate("due_date");
                int priority = resultSet.getInt("prio");
                String professorF = resultSet.getString("Fname");
                String professorL = resultSet.getString("Lname");
                Time start = resultSet.getTime("start_time");
                Time end = resultSet.getTime("end_time");
                boolean complete = resultSet.getBoolean("completed");

                Object[] rowData = {title, dueDate, priority, professorF, professorL, start, end, complete};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

}
