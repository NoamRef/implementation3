package Domain;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

public class Game {
    private int score; // positive->home team winnig
    private Date date;
    private Time time;
    private Stadium place;
    private Team homeTeam;
    private Team outTeam;
    private Refree[] refrees;
    private List<Event> events;

    public Game(Date d1, Time t1, Stadium s1, Team team1, Team team2, Refree[] refs) {
        date = d1;
        time = t1;
        place = s1;
        homeTeam = team1;
        outTeam = team2;
        refrees = refs;
        events = new ArrayList<Event>();
    }

    // fucntions
    public void updateScore(int score) {
        this.score = score;
    }

    public void addEvent(Event e) {
        events.add(e);
    }
}
