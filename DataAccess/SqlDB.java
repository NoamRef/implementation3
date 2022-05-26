package DataAccess;

import Domain.Refree;
import Domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqlDB {

    private static final SqlDB instance = new SqlDB(); // singelton
    DBConnector dbc = DBConnector.getInstance();
    Connection connection;

    // private constructor to avoid client applications to use constructor
    public static SqlDB getInstance() {
        return instance;
    }

    private SqlDB() {
        dbc = DBConnector.getInstance();
        connection = DBConnector.getConnection();
    }

    public boolean checkUserNameExistsInDB(String u1) {
        try {
            String query = "select COUNT(*) from users where username='" + u1 + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getInt(1) > 0) // not empty => user Exists
            {
                System.out.println("Error: User Name Exists");
                return true; // error
            }
            return false; // good
        } catch (Exception e) {
            System.out.println(e.toString());
            return true;
        }
    }

    public void Adduser(User user, String password) {
        try {

            Statement stmt = connection.createStatement();
            String role = user.getClass().getName().replace("Domain.", "");
            String sql = "INSERT INTO users(username,password,firstname,role) " +
                    "VALUES ('" + user.getUserName() + "','" + password + "','" + user.getFirstName() + "','"
                    + role
                    + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }

    // refree details
    public List<Refree> refreeLoad() {
        List<Refree> refs = null;
        try {
            refs = new ArrayList<>();
            String username, firstname;
            int id;
            Statement stmt = connection.createStatement();
            String sqlQuery = "select id,username,firstname from users where role='Refree'";
            ResultSet rs = stmt.executeQuery(sqlQuery);
            while (rs.next()) {
                id = rs.getInt(1);
                username = rs.getString(2);
                firstname = rs.getString(3);
                refs.add(new Refree(username, firstname, id));
            }
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
        return refs;
    }

    // season details
    public int SeasonIDbyName(String name) {
        try {
            String query = "select id from Seasons where seasonName='" + name + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return (rs.getInt(1));
        } catch (Exception e) {
            return -1; // Season Name doesnt exists
        }
    }

    public int LeagueIDbyName(String name) {
        try {
            String query = "select id from leagues where leagueName='" + name + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return (rs.getInt(1));
        } catch (Exception e) {
            return -1; // League Name doesnt exists
        }
    }

    public void AddTeam(String teamName, int leagueID, int seasonID) {
        try {

            Statement stmt = connection.createStatement();
            String sql = "insert into Teams(TeamName,leagueID,SeasonID)" +
                    "VALUES ('" + teamName + "'," + leagueID + "," + seasonID + ");";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }

    public boolean checkLogInDetails(String name, String pass) {
        try {
            String query = "select COUNT(*) from users where username='" + name + "' and password='" + pass + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getInt(1) == 0) // not empty => user Exists with this password
            {
                System.out.println("UserName or Password are incorrect");
                return false; // error
            }
            return true; // good
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public String[] getUserDetailsByUName(String name) {
        try {
            String query = "select id,firstName,role from users where username='" + name + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String[] details = new String[3];
            details[0] = rs.getString(1); // ID
            details[1] = rs.getString(2); // FirstName
            details[2] = rs.getString(3); // Role
            return details;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public boolean CheckIfRefExistInPlacment(int id, int leagueID, int SeasonID) {
        try {
            String query = "select COUNT(*) from RefreePlacment where RefreeID=" + Integer.toString(id)
                    + " and SeasonID=" + Integer.toString(SeasonID) + " and leagueID=" + Integer.toString(leagueID);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getInt(1) == 0) // not empty => user Exists with this password
            {
                return false; // good
            }
            return true; // bad ref exits
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean CheckTeamNumInLeague(String leagueID, String SeasonID) {
        try {
            String query = "select COUNT(*) from Teams where leagueID=" + Integer.valueOf(leagueID)
                    + " and SeasonID=" + Integer.valueOf(SeasonID);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.getInt(1) < 4) {
                return false; // good
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public void EnrollRef(int id, int leagueID, int SeasonID) {
        try {

            Statement stmt = connection.createStatement();
            String sql = "insert into RefreePlacment(RefreeID,SeasonID,leagueID) values(" + id + "," + SeasonID + ","
                    + leagueID + ")";
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }

    }

    /**
     * db function that used for testing
     * 
     * 
     * 
     */

    public List<Integer> getRefreeIds() {
        try {
            String query = "select id from users where role='Refree'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            List<Integer> ids = new ArrayList<Integer>();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
            return (ids);
        } catch (Exception e) {
            return null; // No refrees
        }
    }

    public void deleteUser(String name) {
        String sql = "DELETE FROM users WHERE username = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, name);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void CleanRefreePlacment() {
        String sql = "DELETE FROM RefreePlacment";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createGameSchedule() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE GameSchedule" +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "gameID varchar(255), leagueID varchar(255), SeasonID varchar(255)," +
                    " homeTID varchar(255), awayTID varchar(255), weekNum varchar(255),gameDate varchar(255));";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void AddGameToGameSchedule(String gameID, String leagueID, String seasonID, String homeTID, String awayTID,
            String weekNum, String date) {
        try {
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO GameSchedule(gameID, leagueID, SeasonID, homeTID, awayTID, weekNum, gameDate) " +
                    "VALUES ('" + gameID + "','" + leagueID + "','" + seasonID + "','" + homeTID + "','" + awayTID
                    + "','"
                    + weekNum + "','" + date
                    + "');";
            System.out.println(sql);
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            System.out.println(e.toString());
        }
    }

    public ArrayList<String> getTeamsByLeagueIDandSeasonID(String leagueID, String seasonID) {
        ArrayList<String> teams = new ArrayList<>();
        try {
            String query = "select id, TeamName from Teams where leagueID=='" + leagueID + "' and SeasonID=='"
                    + seasonID + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                teams.add(rs.getString("id"));
                // teams.add(rs.getString("TeamName"));
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return teams;
    }

}
