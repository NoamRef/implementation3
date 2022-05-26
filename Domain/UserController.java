
package Domain;

import DataAccess.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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

    public User LoginUser(String userName, String password) {
        if (!ud.checkLogInDetails(userName, password)) {
            return null; // details are unvalid
        }
        String[] details = ud.getUserDetailsByUName(userName);
        User u = createObjectByName(details[2], userName, details[1]);
        return u;

    }

    public void DeleteUser(String name) {
        ud.deleteUser(name);
    }

    public void DeleteEnrollTable() {
        ud.CleanRefreePlacment();
    }

    public void DeleteGamesTable() {
        ud.CleanGameSchedule();
    }

    public int RefreePlacement(String season, String league, String[] names) {
        int count = 0;
        int SeasonID = ud.SeasonIDbyName(season);
        if (SeasonID == -1) {
            System.out.println("Season Name doesn't exist");
            return -1;
        }
        Season seasonObject = new Season(Integer.parseInt(season.substring(0, 4)));
        int LeagueID = ud.LeagueIDbyName(league);
        if (LeagueID == -1) {
            System.out.println("League Name doesn't exist");
            return -2;
        }
        League LeagueObject = new League(league, seasonObject);
        System.out.println("Sucssesfully loded:\n" + LeagueObject);
        // will enroll only names that can be enroll, others will aborted(Change
        // Acceptance Testing for this) => added support for this aswll
        List<Refree> refs = ud.refreeLoad();
        Iterator iterator = refs.iterator();
        while (iterator.hasNext()) {
            Refree curr = (Refree) iterator.next();
            String name = curr.getUserName();
            int refID = curr.getID();
            for (String reftoADD : names) {
                if (reftoADD.equals(name)) {
                    if (ud.CheckIfRefExistInPlacment(refID, LeagueID, SeasonID)) {
                        System.out.println(name + " Aleady exists in this season and legue. FAIL to enroll him");
                        count = count + 1;
                    } else {
                        ud.EnrollRef(refID, LeagueID, SeasonID);
                        System.out.println(name + " Was enrolled sucssefully");
                    }
                }
            }
        }
        if (count == names.length) {
            return -5; // all refrees alrady there
        }
        return 1; // sucsses
    }

    public int preparingGamesSchedule(String leagueName, String seasonName) {
        int season = ud.SeasonIDbyName(seasonName);
        if (season == -1) {
            System.out.println("Season Name doesn't exist");
            return -1;
        }
        int league = ud.LeagueIDbyName(leagueName);
        if (league == -1) {
            System.out.println("League Name doesn't exist");
            return -2;
        }
        int PolicyID = ud.LeaguePolicyIDbyName(leagueName);
        String PolicyName = ud.policyNameByID(PolicyID);
        if (PolicyName == null) {
            System.out.println("No policy apeended for this league and season");
            return -3; // if enter here there is a bug in
        }
        // create policy object
        Game_assiging_policy policy = null;
        String type = "";
        if (PolicyName.equals("Cup")) {
            policy = new Game_assiging_policy_cup();
            type = "Cup";
        } else if (PolicyName.equals("League")) {
            policy = new Game_assiging_policy_league();
            type = "League";

        }
        Season seasonObject = new Season(Integer.parseInt(seasonName.substring(0, 4)), policy);
        League LeagueObject = new League(leagueName, seasonObject);
        System.out.println("Sucssesfully loded:\n" + LeagueObject);

        List<Team> teams = ud.LoadTeams(league, season);
        if (teams == null) {
            return -2; // not enough teams
        }
        List<Game> games = seasonObject.getPolicy().Apply(teams, seasonObject.returnSeason()); // getting games by
                                                                                               // policy
        if (ud.SaveGames(games, league, season, type)) {
            return 1;
        }
        return 0; // problem accured
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
