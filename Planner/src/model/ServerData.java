package model;

/**
 * This class contains data to access database and schemas in database.
 *
 * @author Ingeun Hwang, Khin Win
 *
 */
public class ServerData {
    /** The Database addresss */
    public static final String DB_URL = "jdbc:mysql://localhost:3306/planner_ddl";
    /** The Database username */
    public static final String DB_USERNAME = "root";
    /** The Database password */
    public static final String DB_PASSWORD = "pleasegiveusATom";
    /** The User_table */
    public static final String USER_TABLE = "USER_TABLE";
    /** The Event_table */
    public static final String EVENT_TABLE = "EVENT_TABLE";
    /** The Assignment_detail_table */
    public static final String ASSIGNMENT_DETAIL_TABLE = "ASSIGNMENT_DETAIL_TABLE";
    /** The Professor_table */
    public static final String PROF_TABLE = "PROFESSOR_TABLE";
    /** The Time_table */
    public static final String TIME_TABLE = "TIME_TABLE";
}
