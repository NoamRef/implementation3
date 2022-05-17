
package Domain;

import DataAccess.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserController {
    SqlDB ud;
    List<Stadium> stadiums = new ArrayList<>();
    List<Team> teams = new ArrayList<>();
    List<Season> seasons = new ArrayList<>();
    List<Policy> policys = new ArrayList<>();
    List<League> leagues = new ArrayList<>();
    List<Refree> refrees = new ArrayList<>();

    public UserController() {
        ud = SqlDB.getInstance();
    }
    
    public int createGameSchedule() {
        try {
            ud.createGameSchedule();
        } catch (Exception ex) {
            throw new RuntimeException("createGameSchedule failed", ex);
        }
        return 1; // sucsses
    } 

    public int AddTeam(String teamName, int leagueID, int SeasonID) {
        // if (ud.checkTeamNameExistsInDB(teamName)) // check details
        // {
        //     return -1; // user exists
        // }
        try {
            ud.AddTeam(teamName, leagueID, SeasonID);
        } catch (Exception ex) {
            throw new RuntimeException("Error Adding User", ex);
        }
        return 1; // sucsses
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
        ud.deleteUser(name);
    }

    public User LoginUser(String userName, String password) {
        if (!ud.checkLogInDetails(userName, password)) {
            return null; // details are unvalid
        }
        String[] details = ud.getUserDetailsByUName(userName);
        User u = createObjectByName(details[2], userName, details[1]);
        return u;

    }

    public int RefreePlacement(String season, String league, String[] names) {
        int SeasonID = ud.SeasonIDbyName(season);
        if (SeasonID == -1) {
            System.out.println("Season Name doesn't exist");
            return -1;
        }
        int LeagueID = ud.LeagueIDbyName(league);
        if (LeagueID == -1) {
            System.out.println("League Name doesn't exist");
            return -2;
        }
        int refID;
        // will enroll only names that can be enroll, others will abort(Change
        // Acceptance Testing for this)
        for (String refFirstName : names) {
            refID = ud.RefreeIDbyName(refFirstName);
            if (refID == -1) {
                System.out.println(refFirstName + " is not a refree or not exits. FAIL to enroll him");
            } else if (ud.CheckIfRefExistInPlacment(refID, LeagueID, SeasonID)) {
                System.out.println(refFirstName + " Aleady exists in this season and legue. FAIL to enroll him");
            } else {
                ud.EnrollRef(refID, LeagueID, SeasonID);
                System.out.println(refFirstName + " Was enrolled sucssefully");
            }
        }
        return 1; // sucsses
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

    public int preparingGamesSchedule(String leagueID, String seasonID, ArrayList<String> teamsIDList, ArrayList<String> GameDates)
    {
        int season = ud.SeasonIDbyName(seasonID);
        if ( season == -1) {
            System.out.println("Season Name doesn't exist");
            return -1;
        }
        int league = ud.LeagueIDbyName(leagueID);
        if (league == -1) {
            System.out.println("League Name doesn't exist");
            return -2;
        }

        if (ud.CheckTeamNumInLeague(leagueID, seasonID))
        {
            System.out.println("Not Enough Teams");
            return -2;
        }

        int gameID = 1;
        ArrayList<GameSchedule> gamesScheduleDetailsArray = new ArrayList<>();// GameID(LeagueID-SeasonID-RoundID-GameID), homeTeamID, awayTeamID, WeekNumber
        // for (int i = 0; i < teamsIDList.size(); i++) {
        //     int week = 1;
        //     for (int j = 0; j < teamsIDList.size(); j++) {
        //         if (i==j){continue;}
        //         String gid = leagueID+seasonID+Integer.valueOf(j+1)+gameID;
        //         String Homeid = teamsIDList.get(i);
        //         String Awayid = teamsIDList.get(j);
        //         String WeekNumber = Integer.toString(week);
        //         String date = GameDates.get(j);
        //         GameSchedule newGame = new GameSchedule(gid, Homeid, Awayid, WeekNumber, date);
        //         gamesScheduleDetailsArray.add(newGame);
        //         gameID++; 
        //         week++;        
        //     }
        // }

        for (int i = 0; i < teamsIDList.size(); i=i+2) {
            String gid = Integer.toString(gameID);
            String Homeid = teamsIDList.get(i);
            String Awayid = teamsIDList.get(i+1);
            String WeekNumber = "Quarter-final";
            String date = GameDates.get(0);
            GameSchedule newGame = new GameSchedule(gid,leagueID,seasonID, Homeid, Awayid, WeekNumber, date);
            gamesScheduleDetailsArray.add(newGame);
            gameID++; 
        }

        for (int i = 0; i < teamsIDList.size()/4;i++) {
            String gid = Integer.toString(gameID);
            String Homeid = "";
            String Awayid = "";
            String WeekNumber = "Semi-final";
            String date = GameDates.get(1);
            GameSchedule newGame = new GameSchedule(gid,leagueID,seasonID, Homeid, Awayid, WeekNumber, date);
            gamesScheduleDetailsArray.add(newGame);
            gameID++; 
        }

        String gid = Integer.toString(gameID);
        String Homeid = "";
        String Awayid = "";
        String WeekNumber = "Final";
        String date = GameDates.get(2);
        GameSchedule newGame = new GameSchedule(gid,leagueID,seasonID, Homeid, Awayid, WeekNumber, date);
        gamesScheduleDetailsArray.add(newGame);

        for (GameSchedule gs : gamesScheduleDetailsArray) {
            ud.AddGameToGameSchedule(gs.GameID, gs.LeagueID, gs.SeasonID,gs.homeTeamID,gs.awayTeamID
            ,gs.weekNumber ,gs.Date);
        }
        return 0;
    }

}
