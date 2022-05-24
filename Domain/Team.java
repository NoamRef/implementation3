package Domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String Name;
    private List<League> leagues;
    private Coach coach;
    private List<Player> players;
    private int points;

    public Team(String name) {
        leagues = new ArrayList<League>();
        players = new ArrayList<Player>();
        Name = name;
        points = 0;
        coach = null;
    }

    public Team(String name, Coach c1) {
        leagues = new ArrayList<League>();
        players = new ArrayList<Player>();
        Name = name;
        points = 0;
        coach = c1;
    }

    // getters and setter
    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.Name;
    }

    public void setCoach(Coach c1) {
        this.coach = c1;
    }

    public void Addplayer(Player p1) {
        this.players.add(p1);
    }

    public String getCoachUName() {
        return this.coach.getUserName();
    }

    public String getPlayers() {
        String s = "";
        for (Player p : players) {
            s = s + p.getFirstName() + "\n";
        }
        return s;
    }
}
