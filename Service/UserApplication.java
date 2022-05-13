package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

// system
public class UserApplication {
    UserController uc = new UserController();

    public void ResgisterUser(String uName, String pass, String FirstName) {
        uc.insertUser(uName, pass, FirstName);
    }
}
