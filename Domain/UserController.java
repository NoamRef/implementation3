
package Domain;

import DataAccess.*;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    SqlDB ud;

    public UserController() {
        ud = SqlDB.getInstance();
    }

    public int ResgisterUser(String u1, String p1, String f1, String role) {
        if (ud.checkUserNameExistsInDB(u1)) // check details
        {
            return -1; // user exists
        }
        if (!role.equals("Fan") && !role.equals("Representative") && !role.equals("Refree") && !role.equals("Coach")
                && !role.equals("Player")) {
            System.out.println("Error: role choice unvalid");
            return -2; // role unvalid
        }
        User u = createObjectByName(role, u1, f1);
        try {
            ud.Adduser(u, p1);
        } catch (Exception ex) {
            throw new RuntimeException("Error Adding User", ex);
        }
        return 1; // sucsses
    }

    public void DeleteUser(String name) {
        ud.delete(name);
    }

    public User LoginUser(String userName, String password) {
        if (!ud.checkLogInDetails(userName, password)) {
            return null; // details are unvalid
        }
        String[] details = ud.getUserDetailsByUName(userName);
        User u = createObjectByName(details[2], userName, details[1]);
        return u;

    }

    // private function to create the object we need
    private User createObjectByName(String objName, String u1, String f1) {
        User u = null;
        if (objName.equals("Fan")) {
            u = new Fan(u1, f1);
        } else if (objName.equals("Representative")) {
            u = new Representative(u1, f1);
        } else if (objName.equals("Refree")) {
            u = new Refree(u1, f1);
        } else if (objName.equals("Coach")) {
            u = new Coach(u1, f1);
        } else if (objName.equals("Player")) {
            u = new Player(u1, f1);
        }
        return u;
    }

}
