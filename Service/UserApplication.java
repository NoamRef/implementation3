package Service;

import Domain.*;

import java.sql.SQLException;
import java.util.*;

// system
public class UserApplication {
    User LoggedIn = null;
    String UserRole = "Guest";
    UserController uc = new UserController();

    public void ResgisterUser(String u1, String p1, String f1, String role) {
        uc.ResgisterUser(u1, p1, f1, role);
    }

    public void LoginUser(String uName, String Pass) {
        if (!UserRole.equals("Guest")) {
            System.out.println("You are already loggedIn, logout");
            return;
        }
        LoggedIn = uc.LoginUser(uName, Pass);
        if (LoggedIn != null) {
            UserRole = LoggedIn.getClass().getName().replace("Domain.", "");
            System.out.println("Logged in Sucssefully as " + UserRole);
        } else {
            System.out.println("Failed to login");
        }
    }

    public void WhosISloggedIN() {
        System.out.println(UserRole);
    }

    public void LogOut() {
        LoggedIn = null;
        UserRole = "Guest";
        System.out.println("Logged out Sucssefully");
    }

    public int RefreePlacement(String Season, String league, String[] names) {
        if (!UserRole.equals("Representative")) {
            System.out.println("You dont have the right privileges");
            return -9;
        }
        int code = uc.RefreePlacement(Season, league, names);
        if (code == 1) {
            System.out.println("Refree placment ended sucssefully for league " + league + " and season " + Season);
        } else {
            System.out.println("Problem with refree placment");
        }
        return code;
    }
}
