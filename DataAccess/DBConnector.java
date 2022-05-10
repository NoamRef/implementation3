package DataAccess;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnector {
    public static final String URL = "jdbc:mysql://localhost:3306/";
    public static final String USER = "root";
    public static final String PASS = "root";

    private static final DBConnector instance = new DBConnector();

    //private constructor to avoid client applications to use constructor
    public static DBConnector getInstance(){
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
            //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //return DriverManager.getConnection(URL, USER, PASS);
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root", "root");

            return conn;
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
     /**
         * Test Connection
         */


}
