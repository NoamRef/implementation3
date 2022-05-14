package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

// system
public class UserApplication {
    User LoggedIn = null;
    String UserRole = "";
    UserController uc = new UserController();

    public void ResgisterUser(String u1, String p1, String f1, String role) {
        uc.ResgisterUser(u1, p1, f1, role);
    }

    public void LoginUser(String uName, String Pass) {
        LoggedIn = uc.LoginUser(uName, Pass);
        if (LoggedIn != null) {
            UserRole = LoggedIn.getClass().getName().replace("Domain.", "");
            System.out.println("Logged in Sucssefully");
        } else {
            System.out.println("Failed to login");
        }
    }

    public void WhosISloggedIN() {
        System.out.println(UserRole);
    }

    public void LogOut() {
        LoggedIn = null;
        UserRole = "";
        System.out.println("Logged out Sucssefully");
    }
}
