package Domain;

import java.security.Policy;

public class Game_assiging_policy extends Policy {
    String name = "Defualt";

    public Game_assiging_policy(String name) {
        this.name = name;
    }

    public Game_assiging_policy() {
        this.name = name;
    }

    public String Apply() {
        return ("Applying " + name);
    }
}
