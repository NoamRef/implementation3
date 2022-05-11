package Domain;

import java.sql.Date;
import java.sql.Time;

import javax.xml.crypto.Data;

public class Game {
    private int Score;
    private Date date;
    private Time time;
    private Stadium place;
    private Team homeTeam;
    private Team outTeam;
    private Refree[] refrees;

    public Game(Date d1, Time t1, Stadium s1, Team team1, Team team2, Refree[] refs) {
        date = d1;
        time = t1;
        place = s1;
        homeTeam = team1;
        outTeam = team2;
        refrees = refs;
    }
}
