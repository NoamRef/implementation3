
package Domain;

import DataAccess.*;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    SqlDB ud;

    public UserController() {
        ud = SqlDB.getInstance();
    }

    public void ResgisterUser(String u1, String p1, String f1, String role) {
        if (ud.checkUserName(u1)) // check details
        {
            return;
        }
        User u = new User(u1, f1);
        if (!role.equals("Fan") && !role.equals("Representative") && !role.equals("Refree")) {
            System.out.println("Error with role choice");
            return;
        }
        if (role.equals("Fan")) {
            u = new Fan(u1, f1);
        }
        if (role.equals("Representative")) {
            u = new Representative(u1, f1);
        }
        if (role.equals("Refree")) {
            u = new Refree(u1, f1);
        }
        try {
            ud.Adduser(u, p1);
        } catch (Exception ex) {
            throw new RuntimeException("Error Adding User", ex);
        }
    }

}
