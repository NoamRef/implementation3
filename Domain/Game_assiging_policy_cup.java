package Domain;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.plaf.TreeUI;

public class Game_assiging_policy_cup extends Game_assiging_policy {
    public Game_assiging_policy_cup() {
        super("Cup");
    }

    @Override
    public List<Game> Apply(List<Team> teams, int year) {
        List<Game> games = new ArrayList<>();
        Random rand = new Random();
        int id = 1;
        int size = teams.size();
        int day = 1;
        boolean firstRun = true;
        String String_year = String.valueOf(year);
        // rendom, 1v1's
        while (size > 1) {
            for (int i = 0; i < size / 2; i++) {
                Team HomeTeam = null; // if null than game is TBA(TO BE ANNONCED +> depends on winings)
                Team AwayTeam = null;
                if (firstRun) {
                    int randomIndex = rand.nextInt(teams.size());
                    HomeTeam = teams.get(randomIndex);
                    teams.remove(randomIndex);
                    randomIndex = rand.nextInt(teams.size());
                    AwayTeam = teams.get(randomIndex);
                    teams.remove(randomIndex);
                }
                // set a date and add to game
                games.add(new Game(id, String_year + "-" + String.valueOf(day) + "-9 21:00", HomeTeam, AwayTeam));
                id += 1;
            }
            firstRun = false;
            day = day + 3;
            size = size / 2;
        }
        return games;
    }
}
