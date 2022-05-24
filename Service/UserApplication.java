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

    public void createGameSchedule() {
        uc.createGameSchedule();
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

    public void AddTeam(String teamName, int leagueID, int SeasonID) {
        uc.AddTeam(teamName, leagueID, SeasonID);
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

    public int preparingGamesSchedule( String leagueName, String seasonName,String leagueID, String SeasonID, ArrayList<String> teamsIDList, ArrayList<String> GameDates) {
        if (!UserRole.equals("Representative")) {
            System.out.println("You dont have the right privileges");
            return -9;
        }
        int code = uc.preparingGamesSchedule(leagueName,seasonName, leagueID, SeasonID, teamsIDList, GameDates);
        if (code == 0) {
            System.out.println("preparing Games Schudule ended sucssefully for league " + leagueID + " and season " + SeasonID);
        } else {
            System.out.println("Problem with refree placment");
        }
        return code;
    }
}
