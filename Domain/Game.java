package Domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private int id;
    private int score; // positive->home team winnig
    private String date; // date includes time
    private Stadium place;
    private Team homeTeam;
    private Team outTeam;
    private Refree[] refrees;
    private List<Event> events;

    public Game(String d1, Stadium s1, Team team1, Team team2, Refree[] refs) {
        date = d1;
        place = s1;
        homeTeam = team1;
        outTeam = team2;
        refrees = refs;
        events = new ArrayList<Event>();
    }

    public Game(int id, String d1, Team team1, Team team2) {
        place = null;
        refrees = null;

        this.id = id;
        date = d1;
        homeTeam = team1;
        outTeam = team2;
        events = new ArrayList<Event>();
    }

    // fucntions
    public void updateScore(int score) {
        this.score = score;
    }

    public int returnScore() {
        return score;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public String returnEventList() {
        String s = "";
        for (Event e : events) {
            s = s + e.returnDesc() + "\n";
        }
        return s;
    }

    public void setPlace(Stadium st) {
        this.place = st;
    }

    public void setReferees(Refree[] refs) {
        this.refrees = refs;
    }

    public int getID() {
        return this.id;
    }

    public Team getHomeTeam() {
        return this.homeTeam;
    }

    public Team getAwayTeam() {
        return this.outTeam;
    }

    public String GetDate() {
        return this.date;
    }
}
