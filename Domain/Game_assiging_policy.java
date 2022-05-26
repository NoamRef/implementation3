package Domain;

import java.security.Policy;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

public class Game_assiging_policy extends Policy {
    String name = "Defualt";

    public Game_assiging_policy(String name) {
        this.name = name;
    }

    public Game_assiging_policy() {
        this.name = name;
    }

    public List<Game> Apply(List<Team> teams, int year) {
        System.out.println("No applying in Defualt");
        return null;
    }

    public String getName() {
        return name;
    }
}
