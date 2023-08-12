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

    public void deleteRow(Object theAssignmentID) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "DELETE FROM " + ServerData.EVENT_TABLE +
                    " WHERE assignment_id = ?";

            PreparedStatement deleteStatement = connection.prepareStatement(query);
            deleteStatement.setString(1, String.valueOf(theAssignmentID));

            int rowsDeleted = deleteStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Row deleted successfully.");
            } else {
                System.out.println("No rows deleted.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editRow(Object theAssignmentID, int theCol, Object theNewValue) {
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String attribute = "";
            if(theCol == 1) {
                attribute = "title";
            } else if(theCol == 2) {
                attribute = "due_date";
            } else if(theCol == 3) {
                attribute = "prio";
            } else if(theCol == 4) {
                attribute = "Fname";
            } else if(theCol == 5) {
                attribute = "Lname";
            } else if(theCol == 6) {
                attribute = "start_time";
            } else if(theCol == 7) {
                attribute = "end_time";
            } else if(theCol == 8) {
                attribute = "completed";
            }


            String query = "UPDATE " + ServerData.USER_TABLE +
                    " NATURAL JOIN " + ServerData.EVENT_TABLE +
                    " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE +
                    " NATURAL JOIN " + ServerData.PROF_TABLE +
                    " NATURAL JOIN " + ServerData.TIME_TABLE  +
                    " SET " + attribute + " = ?"  + " WHERE assignment_id = ?";

            PreparedStatement statement = connection.prepareStatement(query);


            statement.setString(1, String.valueOf(theNewValue));
            statement.setString(2, String.valueOf(theAssignmentID));

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Row deleted successfully.");
            } else {
                System.out.println("No rows deleted.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Object[]> searchDefault(String theUsername, Date theStartDate, Date theEndDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT * FROM " + ServerData.EVENT_TABLE + " E " +
                    " JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE + " A ON E.assignment_id = A.assignment_id" +
                    " JOIN " + ServerData.TIME_TABLE + " T ON A.assignment_id = T.assignment_id" +
                    " JOIN " + ServerData.PROF_TABLE + " P ON T.assignment_id = P.assignment_id" +
                    " WHERE username = ? AND A.due_date >= ? AND A.due_date <= ?";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theUsername);
            checkStatement.setDate(2, theStartDate);
            checkStatement.setDate(3, theEndDate);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int assignment_id = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                Date dueDate = resultSet.getDate("due_date");
                int priority = resultSet.getInt("prio");
                String professorF = resultSet.getString("Fname");
                String professorL = resultSet.getString("Lname");
                Time start = resultSet.getTime("start_time");
                Time end = resultSet.getTime("end_time");
                boolean complete = resultSet.getBoolean("completed");

                Object[] rowData = {assignment_id, title, dueDate, priority, professorF, professorL, start, end, complete};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Object[]> searchByPriority(String theUsername, int thePriority, Date theDueDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT assignment_id, title, prio, due_date FROM " + ServerData.EVENT_TABLE +
                    " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE +
                    " WHERE prio >= ? AND username IN (SELECT username " +
                    " FROM event_table " +
                    " WHERE username = ? AND due_date >= ?" +
                    " GROUP BY  assignment_id) ";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setInt(1, thePriority);
            checkStatement.setString(2, theUsername);
            checkStatement.setDate(3, theDueDate);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int assignment_id = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                int priority = resultSet.getInt("prio");
                Date dueDate = resultSet.getDate("due_date");

                Object[] rowData = {assignment_id, title,  priority, dueDate};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Object[]> searchByProfessor(String theProfLastName, Date theStartDate, Date theEndDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT assignment_id, title FROM (SELECT * FROM ( " + ServerData.EVENT_TABLE + "" +
                    " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE + " NATURAL JOIN " + ServerData.PROF_TABLE + ")) AS A " +
                    " WHERE A.Lname = ? AND  A.due_date >= ? AND " +
                    " EXISTS (SELECT * " +
                    " FROM (SELECT * FROM ( " + ServerData.EVENT_TABLE  +
                    " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE +
                    " NATURAL JOIN " + ServerData.PROF_TABLE + " )) AS B " +
                    " WHERE B.Lname = ? AND B.due_date <= ? AND A.assignment_id = B.assignment_id); ";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theProfLastName);
            checkStatement.setDate(2, theStartDate);
            checkStatement.setString(3, theProfLastName);
            checkStatement.setDate(4, theEndDate);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int assignment_id = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");

                Object[] rowData = {assignment_id,  title};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }



    public ArrayList<Object[]> searchByTimeSpentOnAssignment(String theUsername, Date theStartDate, Date theEndDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT title, TIMEDIFF(end_time, start_time) AS total_time " +
                    " FROM " + ServerData.EVENT_TABLE + " NATURAL JOIN " + ServerData.TIME_TABLE + " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE +
                    " WHERE username = ? " +
                    " AND due_date >= ? AND due_date <= ?;";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theUsername);
            checkStatement.setDate(2, theStartDate);
            checkStatement.setDate(3, theEndDate);


            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("title");
                Time timeTook = resultSet.getTime("total_time");

                Object[] rowData = {username, timeTook};
                res.add(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Object[]> searchCompletedAssignment(String theUsername, Date theStartDate, Date theEndDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT assignment_id, title, Fname, Lname " +
                    " FROM " + ServerData.EVENT_TABLE + " NATURAL JOIN " + ServerData.PROF_TABLE + " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE +
                    " WHERE completed = 1 AND due_date > ? AND due_date < ? AND username = ?;";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setDate(1, theStartDate);
            checkStatement.setDate(2, theEndDate);
            checkStatement.setString(3, theUsername);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int assignmentID = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                String fname = resultSet.getString("Fname");
                String lname = resultSet.getString("Lname");

                Object[] rowData = {assignmentID, title, fname, lname};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
    public ArrayList<Object[]> searchByTotalTimeSpent(String theUsername, Date theStartDate, Date theEndDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT  username,  SEC_TO_TIME(SUM(TIME_TO_SEC((TIMEDIFF(end_time, start_time))))) AS total_time_spent " +
                    " FROM " + ServerData.EVENT_TABLE + " NATURAL JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE + " NATURAL JOIN " + ServerData.TIME_TABLE +
                    " WHERE username = ? AND due_date > ? AND due_date < ?;";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theUsername);
            checkStatement.setDate(2, theStartDate);
            checkStatement.setDate(3, theEndDate);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String timeSpent = resultSet.getString("total_time_spent");

                Object[] rowData = {username, timeSpent};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }

    public ArrayList<Object[]> searchNumberOfAssignment(String theUsername, Date theStartDate, Date theEndDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT username, COUNT(title) " +
                    " FROM ( " +
                    "    SELECT ut.username, et.title " +
                    "    FROM " + ServerData.USER_TABLE + " ut " +
                    "    JOIN " + ServerData.EVENT_TABLE + " et USING (username) " +
                    "    JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE + " adt USING (assignment_id) " +
                    "    WHERE ut.username = ? " +
                    "        AND adt.due_date >= ? AND adt.due_date <= ? " +
                    "    UNION " +
                    "    SELECT ut.username, et.title " +
                    "    FROM " + ServerData.USER_TABLE + " ut " +
                    "    JOIN " + ServerData.EVENT_TABLE + " et USING (username) " +
                    "    JOIN " + ServerData.ASSIGNMENT_DETAIL_TABLE + " adt USING (assignment_id) " +
                    "    WHERE adt.due_date >= ? AND adt.due_date <= ? " +
                    " ) AS combined_data " +
                    " GROUP BY username;";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theUsername);
            checkStatement.setDate(2, theStartDate);
            checkStatement.setDate(3, theEndDate);
            checkStatement.setDate(4, theStartDate);
            checkStatement.setDate(5, theEndDate);


            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int count = resultSet.getInt("COUNT(title)");

                Object[] rowData = {username, count};
                res.add(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Object[]> minimizeView(String theUsername, Date theStartDate) throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT A.assignment_id, title, prio, due_date " +
                    " FROM " + ServerData.EVENT_TABLE + " E " +
                    " LEFT JOIN " +  ServerData.ASSIGNMENT_DETAIL_TABLE + " A ON E.assignment_id = A.assignment_id " +
                    " WHERE E.username = ? AND A.due_date >= ?" +
                    " UNION " +
                    " SELECT A.assignment_id, title, prio, due_date " +
                    " FROM " + ServerData.EVENT_TABLE + " E " +
                    " RIGHT JOIN assignment_detail_table A ON E.assignment_id = A.assignment_id" +
                    " WHERE E.username = ? AND A.due_date >= ?";


            PreparedStatement checkStatement = connection.prepareStatement(query);
            checkStatement.setString(1, theUsername);
            checkStatement.setDate(2, theStartDate);
            checkStatement.setString(3, theUsername);
            checkStatement.setDate(4, theStartDate);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int assignment_id = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                int priority = resultSet.getInt("prio");
                Date dueDate = resultSet.getDate("due_date");

                Object[] rowData = {assignment_id, title,  priority, dueDate};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public ArrayList<Object[]> avgTimeSpentForProf() throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT  Fname, Lname, SEC_TO_TIME(ROUND(AVG(TIME_TO_SEC((TIMEDIFF(end_time, start_time)))))) AS average_time_per_assignment " +
                    " FROM " + ServerData.EVENT_TABLE + " NATURAL JOIN " + ServerData.TIME_TABLE + " NATURAL JOIN " + ServerData.PROF_TABLE +
                    " GROUP BY Fname, Lname " +
                    " ORDER BY average_time_per_assignment DESC;";


            PreparedStatement checkStatement = connection.prepareStatement(query);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                String fname = resultSet.getString("Fname");
                String lname = resultSet.getString("Lname");
                int avgTime = resultSet.getInt("average_time_per_assignment");

                Object[] rowData = {fname, lname, avgTime};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }


    public ArrayList<Object[]> SearchAssignmentTookLongerThanAvg() throws SQLException {
        ArrayList<Object[]> res = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(ServerData.DB_URL, ServerData.DB_USERNAME, ServerData.DB_PASSWORD)) {
            String query = "SELECT E.Assignment_id, E.title, P.Fname, P.Lname, (TIMEDIFF(T.end_time, T.start_time)) AS time_spent " +
                    " FROM " + ServerData.EVENT_TABLE + " E " +
                    " NATURAL JOIN " + ServerData.TIME_TABLE + " T " +
                    " NATURAL JOIN " + ServerData.PROF_TABLE + " P " +
                    " NATURAL JOIN ( " +
                    "    SELECT P1.Fname, P1.Lname, AVG(TIME_TO_SEC(TIMEDIFF(T1.end_time, T1.start_time))) AS avg_time " +
                    "    FROM " + ServerData.TIME_TABLE + " T1 " +
                    "    NATURAL JOIN " + ServerData.PROF_TABLE + " P1 " +
                    "    WHERE T1.end_time IS NOT NULL " +
                    "    GROUP BY P1.Fname, P1.Lname " +
                    " ) AS ProfessorAvgTime " +
                    " WHERE T.end_time IS NOT NULL " +
                    " AND TIME_TO_SEC(TIMEDIFF(T.end_time, T.start_time)) > ProfessorAvgTime.avg_time " +
                    " ORDER BY Lname, Fname";


            PreparedStatement checkStatement = connection.prepareStatement(query);

            ResultSet resultSet = checkStatement.executeQuery();

            while (resultSet.next()) {
                int assignment_id =  resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                String fname = resultSet.getString("Fname");
                String lname = resultSet.getString("Lname");
                String timeSpent = resultSet.getString("time_spent");

                Object[] rowData = {assignment_id, title, fname, lname, timeSpent};
                res.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }




}
