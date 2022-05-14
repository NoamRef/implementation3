package DataAccess;

import Domain.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class SqlDB {

    private static final SqlDB instance = new SqlDB(); // singelton
    DBConnector dbc = DBConnector.getInstance();

    // private constructor to avoid client applications to use constructor
    public static SqlDB getInstance() {
        return instance;
    }

    private SqlDB() {
        dbc = DBConnector.getInstance();
    }

    public boolean checkUserName(String u1) {
        try {
            Connection connection = DBConnector.getConnection();
            String query = "select * from users where username='" + u1 + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getRow() > 0) // not empty => user Exists
            {
                System.out.println("User Name Exists");
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

            Connection connection = DBConnector.getConnection();
            Statement stmt = connection.createStatement();
            String role = user.getClass().getName();
            String[] roleParts = role.split(".");
            String sql = "INSERT INTO users " +
                    "VALUES ('" + user.getUserName() + "','" + password + "','" + user.getFirstName() + "','"
                    + roleParts[1]
                    + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }

    public void update(User user, String[] params) {

    }

    public void delete(User user) {

    }
}
