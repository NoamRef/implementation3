package Tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import Domain.UserController;
import Service.UserApplication;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UnitTests {
    UserController uc = new UserController();
    UserApplication ua = new UserApplication();

    @Test
    @DisplayName("ControllerRegisterTest")
    public void TestUserRegister() {
        //assertEquals(0, uc.ResgisterUser("Test", "pass", "name", "Fan")); // user already exits
        assertEquals(-2, uc.ResgisterUser("NewRefrz", "pass", "name", "Refz")); // role unvalid
        uc.DeleteUser("David");
        assertEquals(1, uc.ResgisterUser("David", "12345", "David", "Representative")); // user should be added
    }

    @Test
    @DisplayName("LoginTest")
    public void LoginTest() {
        assertEquals(-1, uc.ResgisterUser("Test", "pass", "name", "Fan")); // user already exits
    }


    @Test
    @DisplayName("gameSchedule: Invalid Privileges")
    public void gameSchedule_Invalid_Privileges() {
        ua.LoginUser("Ref1", "RefPassword1");
        int INVALID_PRIVILEGES = -9;
        ArrayList<String> dates= new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_PRIVILEGES, ua.preparingGamesSchedule("La Liga","2012-2013","1","7", teamsIDS, dates));
    }

       
    @Test
    @DisplayName("gameSchedule: Invalid Season")
    public void gameSchedule_Invalid_Season() {
        ua.LoginUser("David", "12345");
        int INVALID_SEASON = -1;
        ArrayList<String> dates= new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_SEASON, ua.preparingGamesSchedule("La Liga","2012-2013","1","7", teamsIDS, dates));
    }

    @Test
    @DisplayName("gameSchedule: Invalid LEAGUE")
    public void gameSchedule_Invalid_LEAGUE() {
        ua.LoginUser("Rep", "pass");
        int INVALID_LEAUGE = -2;
        ArrayList<String> dates= new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_LEAUGE, ua.preparingGamesSchedule("Champions League","2019-2020","3","1", teamsIDS, dates));
    }

    @Test
    @DisplayName("gameSchedule: Invalid TEAMS")
    public void gameSchedule_Invalid_TEAMS() {
        ua.LoginUser("Rep", "pass");
        int INVALID_TEAMS = -2;
        ArrayList<String> dates= new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_TEAMS, ua.preparingGamesSchedule("UEFA","2019-2020","3","1", teamsIDS, dates));
    }

    @Test
    @DisplayName("gameSchedule: VALID DETAILS")
    public void gameSchedule_VALID() {
        ua.LoginUser("Rep", "pass");
        int Sucsses = 0;
        ArrayList<String> levels = new ArrayList<>();
        levels.add("Quarter-Final");
        levels.add("Semi-Final");
        levels.add("Final");
        ArrayList<String> dates= new ArrayList<>();
        dates.add("01/01/2023 21:00");
        dates.add("08/01/2023 21:00");
        dates.add("15/01/2023 21:00");
        assertEquals(Sucsses,  ua.preparingGamesSchedule("La Liga","2020-2021","1","4",levels, dates));
    }

}
