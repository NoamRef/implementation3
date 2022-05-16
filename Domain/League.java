package Domain;

import java.util.ArrayList;
import java.util.List;

// changed season and legues in class diagram
public class League {
    private String name;
    private Season season;
    private List<Team> teams;

    public League(String name, Season s) {
        this.name = name;
        this.season = s;
        this.teams = new ArrayList<>();
    }

    public Season getSeason() {
        return season;
    }

    public void addteam(Team t) {
        teams.add(t);
    }
}
