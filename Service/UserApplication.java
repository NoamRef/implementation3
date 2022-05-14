package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

// system
public class UserApplication {
    User LoggedIn;
    UserController uc = new UserController();

    public void ResgisterUser(String u1, String p1, String f1, String role) {
        uc.ResgisterUser(u1, p1, f1, role);
    }
}
