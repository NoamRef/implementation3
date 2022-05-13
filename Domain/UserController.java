
package Domain;

import DataAccess.*;
import java.sql.SQLException;
import java.util.Scanner;

public class UserController {
    SqlDB ud;

    public UserController() {
        ud = SqlDB.getInstance();
    }

    public void insertUser(String uName, String pass, String FirstName) {
        // TODO: check paremetrs
        User u = new User(uName, FirstName);
        Scanner myObj = new Scanner(System.in);
        System.out.println("Choose Roles\n 1-Fan\n 2-Representative\n 3-Refree");
        String choice = myObj.nextLine();
        if (choice == "1") {
            u = new Fan(uName, FirstName);
        }
        if (choice == "2") {
            u = new Representative(uName, FirstName);
        }
        if (choice == "3") {
            u = new Refree(uName, FirstName);
        }
        try {
            ud.Adduser(u, pass);
        } catch (Exception ex) {
            throw new RuntimeException("Error Adding User", ex);
        }
    }

}
