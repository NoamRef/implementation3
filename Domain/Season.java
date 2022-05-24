package Domain;

public class Season {
    private int year;
    private Game_assiging_policy Game_ass_policy;

    public Season(int y, Game_assiging_policy p1) {
        this.year = y;
        this.Game_ass_policy = p1;
    }

    public int returnSeason() {
        return year;
    }
}
