package Domain;

public class Team {
    private String Name;
    // add players and coach?
    private int points;

    public Team(String name) {
        Name = name;
        points = 0;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    public String getName() {
        return this.Name;
    }
}
