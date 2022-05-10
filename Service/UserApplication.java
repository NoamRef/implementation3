package Service;

import DataAccess.UserDao;
import Domain.*;

import java.sql.SQLException;
import java.util.*;

public class UserApplication {
    UserController uc=new UserController();
    public void insertUser(String iName, String iEmail) {
        uc.insertUser(iName, iEmail);
    }
}
