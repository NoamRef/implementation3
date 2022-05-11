package Domain;

public class Season {
    private League league;
    private Team team;
    private int year;
    private Policy Game_ass_policy;

    public Season(int y, League l1, Team t1, Policy p1) {
        this.year = y;
        this.league = l1;
        this.team = t1;
        this.Game_ass_policy = p1;
    }
}
