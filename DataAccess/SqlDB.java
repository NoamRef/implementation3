package DataAccess;

import Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SqlDB {

    private static final SqlDB instance = new SqlDB(); // singelton
    DBConnector dbc = DBConnector.getInstance();
    Connection connection;

    // private constructor to avoid client applications to use constructor
    public static SqlDB getInstance() {
        return instance;
    }

    private SqlDB() {
        dbc = DBConnector.getInstance();
        connection = DBConnector.getConnection();
    }

    public boolean checkUserNameExistsInDB(String u1) {
        try {
            String query = "select COUNT(*) from users where username='" + u1 + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getInt(1) > 0) // not empty => user Exists
            {
                System.out.println("Error: User Name Exists");
                return true; // error
            }
            return false; // good
        } catch (Exception e) {
            System.out.println(e.toString());
            return true;
        }
    }

    public void Adduser(User user, String password) {
        try {

            Statement stmt = connection.createStatement();
            String role = user.getClass().getName().replace("Domain.", "");
            String sql = "INSERT INTO users(username,password,firstname,role) " +
                    "VALUES ('" + user.getUserName() + "','" + password + "','" + user.getFirstName() + "','"
                    + role
                    + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }

    public boolean checkLogInDetails(String name, String pass) {
        try {
            String query = "select COUNT(*) from users where username='" + name + "' and password='" + pass + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getInt(1) == 0) // not empty => user Exists with this password
            {
                System.out.println("UserName or Password are incorrect");
                return false; // error
            }
            return true; // good
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public void delete(String name) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String[] getUserDetailsByUName(String name) {
        try {
            String query = "select id,firstName,role from users where username='" + name + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String[] details = new String[3];
            details[0] = rs.getString(1); // ID
            details[1] = rs.getString(2); // FirstName
            details[2] = rs.getString(3); // Role
            return details;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

}
