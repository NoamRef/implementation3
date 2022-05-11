/**
 * package Domain;
 * import DataAccess.*;
 * 
 * import java.sql.SQLException;
 * 
 * public class UserController {
 * Dao ud;
 * public UserController() {ud = UserDaoSQL.getInstance();
 * }
 * public void insertUser (String iName, String iEmail ) {
 * User aUser= new User(iName, iEmail);
 * try {
 * ud.save(aUser);
 * } catch (Exception e) {
 * e.printStackTrace();
 * }
 * }
 * }
 */
