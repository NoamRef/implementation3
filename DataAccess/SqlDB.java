package DataAccess;

import Domain.User;

import java.sql.Connection;
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

    public void Adduser(User user, String password) {
        try {

            Connection connection = DBConnector.getConnection();
            Statement stmt = connection.createStatement();
            String role = "f";
            System.out.println(user.getClass().getName());
            String sql = "INSERT INTO users " +
                    "VALUES (" + user.getUserName() + "," + password + "," + user.getFirstName() + "," + role + "');";
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
