package Tests;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import Domain.*;
import Service.UserApplication;

import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class unit_Intergraion_Class_tests {
    Time time = new Time(16, 0, 0);
    Date date = new Date(121, 3, 3);
    Coach coach = new Coach("co12345", "Jim");
    User u = new User("Noam1", "Noam");
    Refree[] refs = new Refree[0];
    Refree re = new Refree("ref", "Jon");
    Team t1 = new Team("Arsenal");
    Team t2 = new Team("Liverpool");
    Stadium std = new Stadium("Stadium2");
    Game_assiging_policy pol = new Game_assiging_policy();
    Season season = new Season(2020, pol);
    League league = new League("LegueA", season);
    Game game = new Game(date, time, std, t1, t2, refs);
    Event event = new Event("Goal 1-0 Home Team", "20:42");
    Event event2 = new Event("Goal 1 -1 Away Team", "22:21");
    Player p1 = new Player("NumberOne", "Lionel");

    @Test
    @DisplayName("User:SetGetName")
    public void TestUserSetGetName() {
        assertEquals("Noam1", u.getUserName());
        assertEquals("Noam", u.getFirstName());
        u.setName("Noam34");
        assertEquals("Noam34", u.getUserName());
        assertFalse("Noam1" == u.getUserName());
    }

    @Test
    @DisplayName("Team:SetPoints")
    public void Team_SetPoints() {
        t1.setPoints(21);
        assertEquals(21, t1.getPoints());
    }

    @Test
    // integration
    @DisplayName("Team:SetCoach")
    public void Team_SetCoach() {
        t2.setCoach(coach);
        assertEquals("co12345", t2.getCoachUName());
    }

    @Test
    // integration
    @DisplayName("Team:addPlayers")
    public void Team_addPlayers() {
        t1.Addplayer(p1);
        assertEquals("Lionel\n", t1.getPlayers());
    }

    @Test
    @DisplayName("Stadium:GetName")
    public void Stadium_GetName() {
        assertEquals("Stadium2", std.getName());
    }

    @Test
    @DisplayName("Season:SeasonYear")
    public void Season_SeasonYear() {
        assertEquals(2020, season.returnSeason());
    }

    // integration
    @Test
    @DisplayName("League:SetGetTeams")
    public void League_SetGetTeams() {
        league.addteam(t1);
        league.addteam(t2);
        assertEquals("Arsenal\nLiverpool\n", league.getTeams());
    }

    @Test
    @DisplayName("Event:ReturnDesc")
    public void Event_returnDescriotion() {
        assertEquals("20:42 Goal 1-0 Home Team", event.returnDesc());
        assertEquals("22:21 Goal 1 -1 Away Team", event2.returnDesc());
    }

    @Test
    // integration
    @DisplayName("Game:getScore")
    public void Game_getScore() {
        game.updateScore(-1);
        assertEquals(-1, game.returnScore());
    }

    // integration
    @Test
    @DisplayName("Game:SetGetEvents")
    public void Game_SetGetEvents() {
        game.addEvent(event);
        game.addEvent(event2);
        assertEquals("20:42 Goal 1-0 Home Team\n22:21 Goal 1 -1 Away Team\n", game.returnEventList());
    }
}
