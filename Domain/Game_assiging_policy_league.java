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
    public List<Game> Apply(List<Team> teams) {
        return null;
    }
}
