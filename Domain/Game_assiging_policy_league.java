package Domain;

import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game_assiging_policy_league extends Game_assiging_policy {
    public Game_assiging_policy_league() {
        super("League");
    }

    @Override
    public List<Game> Apply(List<Team> teams, int year) {
        List<Game> games = new ArrayList<>();
        int id = 1;
        int size = teams.size();
        int day = 1;
        int month = 4;
        String String_year = String.valueOf(year);
        // rendom, 1v1's
        for (int i = 0; i < size; i++) {
            Team HomeTeam = null;
            Team AwayTeam = null;
            HomeTeam = teams.get(i);
            for (int j = 0; j < size; j++) {
                AwayTeam = teams.get(j);
                if (j != i) {
                    games.add(new Game(id,
                            String_year + "-" + String.valueOf(day) + "-" + String.valueOf(month) + " 21:00", HomeTeam,
                            AwayTeam));
                    day = day + 1;
                    if (day == 28) {
                        day = 1;
                        month += 1;
                    }
                    id += 1;
                }
            }
        }
        return games;
    }
}
