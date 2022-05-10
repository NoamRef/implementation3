package DataAccess;

import Domain.User;
import java.util.*;

public class UserDao implements Dao<User> {

    private List<User> users = new ArrayList<>();

    private static final UserDao instance = new UserDao();

    // private constructor to avoid client applications to use constructor
    public static UserDao getInstance() {
        return instance;
    }

    private UserDao() {
        // users.add(new User("John", "john@domain.com"));
        // users.add(new User("Susan", "susan@domain.com"));
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override

    public void save(User user) {
        users.add(user);
        System.out.println(users.toString());
    }

    @Override
    public void update(User user, String[] params) {
        // user.setName(Objects.requireNonNull( params[0], "Name cannot be null"));
        // user.setEmail(Objects.requireNonNull( params[1], "Email cannot be null"));

        users.add(user);
        users.toString();
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }
}
