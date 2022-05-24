package Domain;

public class GameSchedule {
    String GameID;
    String LeagueID;
    String SeasonID;
    String homeTeamID;
    String awayTeamID;
    String weekNumber;
    String Date;

    public GameSchedule(String gameID, String leagueID, String seasonID, String homeTID, String awayTID, String weekNum,
            String date) {
        this.GameID = gameID;
        this.LeagueID = leagueID;
        this.SeasonID = seasonID;
        this.homeTeamID = homeTID;
        this.awayTeamID = awayTID;
        this.weekNumber = weekNum;
        this.Date = date;
    }

    public GameSchedule getGameDetailsByGid(String gameID) {
        if (gameID == this.GameID) {
            GameSchedule gs = this;
            return gs;
        }
        return null;
    };
}