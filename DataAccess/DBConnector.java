package DataAccess;

import java.sql.*;
// import java.util.Properties;
// import java.util.logging.Logger;

public class DBConnector {

    public static final String URL = "jdbc:sqlite:C:/Users/HP/Desktop/DB.db"; // change position to your DB on your cp
    public static final String USER = "root";
    public static final String PASS = "root";

    private static final DBConnector instance = new DBConnector();

    // private constructor to avoid client applications to use
    // constructor(Singelton)
    public static DBConnector getInstance() {
        return instance;
    }

    private DBConnector() {

    }

    /**
     * Get a connection to database
     *
     * @return Connection object
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");

            return conn;
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
    /**
     * Test Connection
     */

}
