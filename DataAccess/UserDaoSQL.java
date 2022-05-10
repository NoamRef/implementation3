package DataAccess;

import Domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDaoSQL implements Dao<User> {

    private static final UserDaoSQL instance = new UserDaoSQL();

    // private constructor to avoid client applications to use constructor
    public static UserDaoSQL getInstance() {
        return instance;
    }

    private UserDaoSQL() {
        // users.add(new User("John", "john@domain.com"));
        // users.add(new User("Susan", "susan@domain.com"));
    }

    DBConnector dbc = DBConnector.getInstance();

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {
        try {

            Connection connection = DBConnector.getConnection();
            Statement stmt = connection.createStatement();

            String sql = "INSERT INTO users " +
                    "VALUES (" + user.getName().concat(user.getEmail()).hashCode() + ",'" + user.getName() + "', '"
                    + user.getEmail() + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void delete(User user) {

    }
}
