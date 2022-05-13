package Domain;

public class Coach extends User {
    private Team team = null;

    public Coach(String name, String fname) {
        super(name, fname);
    }

    // getter and setters
    public void setTeam(Team t) {
        this.team = t;
    }

    public void earseTeam() {
        this.team = null;
    }
}
