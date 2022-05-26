package Domain;

public class Season {
    private int year;
    private Game_assiging_policy Game_ass_policy = new Game_assiging_policy();

    public Season(int y, Game_assiging_policy p1) {
        this.year = y;
        this.Game_ass_policy = p1;
    }

    public Season(int y) {
        this.year = y;
    }

    public int returnSeason() {
        return year;
    }

    public String toString() {
        return " year: " + this.year + " with Game_assigmenst_Policy: " + Game_ass_policy.getName();
    }
}
