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

// ua calls uc that class other class => integration test for every UA function will cover all UC
public class main_and_alternative_tests {
    UserController uc = new UserController();
    UserApplication ua = new UserApplication();
    
    // ****  Registeration  ****
    // 1.1
    @Test
    @DisplayName("UserApplication_RegisterUser:RegisterUserSucssed")
    public void UserApplication_RegisterUserSucssed() {
        uc.DeleteUser("David"); // delete from db
        assertEquals(1, ua.ResgisterUser("David", "12345", "David", "Representative")); // user should be added
    }
    
    // 1.2
    @Test
    @DisplayName("UserApplication_RegisterUser:RegisterUserExist")
    public void UserApplication_RegisterUserExist() {
        assertEquals(-1, ua.ResgisterUser("Test", "pass", "name", "Fan")); // user exists
    }

    // 1.3
    @Test
    @DisplayName("UserApplication_RegisterUser:RegisterUserRoleUnvalid")
    public void UserApplication_RegisterUserRoleUnvalid() {
        assertEquals(-2, ua.ResgisterUser("NewRefrz", "pass", "name", "Refz")); // role unvalid
    }

    // **** Login ****
    // 1.4
    @Test
    @DisplayName("UserApplication_LoginIn:LoginUserSucsses")
    public void LoginTestSucsses() {
        assertEquals(1, ua.LoginUser("Test", "Pass"));
    }

    // 1.5
    @Test
    @DisplayName("UserApplication_LoginIn:LoginUserFaild")
    public void LoginUserFaild1() {
        assertEquals(0, ua.LoginUser("4", "Pass"));
    }

    // 1.6
    @Test
    @DisplayName("UserApplication_LoginIn:LoginUserFaild")
    public void LoginTestFaild2() {
        assertEquals(0, ua.LoginUser("Test", "pass"));
    }

    // 1.7
    @Test
    @DisplayName("UserApplication_LogOut:AlreadyLoggedIn")
    public void UserApplication_AlreadyLoggedIn() {
        ua.LoginUser("Test", "Pass");
        assertEquals(-1, ua.LoginUser("Ref1", "RefPassword1"));
    }

    // ***** Logout *****
    //1.8
    @Test
    @DisplayName("UserApplication_LogOut:LoggedOutSucssefully")
    public void UserApplication_LoggedOutSucssefully() {
        ua.LoginUser("Test", "Pass");
        assertEquals(1, ua.LogOut());
    }
    
    // 1.9
    @Test
    @DisplayName("UserApplication_LogOut:LoggedOutFailed")
    public void UserApplication_LoggedOutFailed() {
        assertEquals(-1, ua.LogOut());
    }


    /// ******* 2 - RefreePlacment ******
    //2.1
    @Test
    @DisplayName("UserApplication_Refrees:RefreePlacmentSucsses")
    public void UserApplication_RefreePlacmentSucsses() {
        ua.LoginUser("Rep", "pass");
        uc.DeleteEnrollTable();
        String[] names = new String[2];
        names[0] = "Ref1";
        names[1] = "Ref2";
        assertEquals(1, ua.RefreePlacement("2020-2021", "La Liga", names));
        ua.LogOut();
    }

    // 2.2
    @Test
    @DisplayName("UserApplication_Refrees:FailedByRefreeAleadyExists")
    public void UserApplication_FailedByRefreeAleadyExistse() {
        ua.LoginUser("Rep", "pass");
        uc.DeleteEnrollTable();
        String[] names = new String[2];
        names[0] = "Ref1";
        names[1] = "Ref2";
        ua.RefreePlacement("2019-2020", "La Liga", names);
        assertEquals(-5, ua.RefreePlacement("2019-2020", "La Liga", names));
        ua.LogOut();
    }

    //2.3
    @Test
    @DisplayName("UserApplication_Refrees:FailedBySeasonName")
    public void UserApplication_FailedBySeasonName() {
        ua.LoginUser("Rep", "pass");
        uc.DeleteEnrollTable();
        String[] names = new String[2];
        names[0] = "Ref1";
        names[1] = "Ref2";
        assertEquals(-1, ua.RefreePlacement("202p-2021", "La Liga", names));
        ua.LogOut();
    }

    //2.4
    @Test
    @DisplayName("UserApplication_Refrees:FailedByLeagueName")
    public void UserApplication_FailedByLeagueName() {
        ua.LoginUser("Rep", "pass");
        uc.DeleteEnrollTable();
        String[] names = new String[2];
        names[0] = "Ref1";
        names[1] = "Ref2";
        assertEquals(-2, ua.RefreePlacement("2020-2021", "La GLiDa", names));
        ua.LogOut();
    }


    //2.5
    @Test
    @DisplayName("UserApplication_Refrees:FailedByUserPrivilges")
    public void UserApplication_FailedByUserPrivilges() {
        ua.LoginUser("Test", "Pass");
        uc.DeleteEnrollTable();
        String[] names = new String[2];
        names[0] = "Ref1";
        names[1] = "Ref2";
        assertEquals(-9, ua.RefreePlacement("2020-2021", "La GLiDa", names));
        ua.LogOut();
    }


    /// ***** 3 - gameSchedule *****
    // 3.1 
    @Test
    @DisplayName("UserApplication_gameSchedule: VALID DETAILS assigment for cup")
    public void gameSchedule_VALID_CUP() {
        uc.DeleteGamesTable();
        ua.LoginUser("Rep", "pass");
        int Sucsses = 1;
        assertEquals(Sucsses, ua.preparingGamesSchedule("UEFA", "2022-2023"));
    }

    // 3.2
    @Test
    @DisplayName("UserApplication_gameSchedule: VALID DETAILS assigment for league")
    public void gameSchedule_VALID_LEAGUE() {
        ua.LoginUser("Rep", "pass");
        int Sucsses = 1;
        assertEquals(Sucsses, ua.preparingGamesSchedule("La Liga", "2022-2023"));
    }

    // 3.3
    @Test
    @DisplayName("UserApplication_gameSchedule: Invalid LEAGUE")
    public void gameSchedule_Invalid_LEAGUE() {
        ua.LoginUser("Rep", "pass");
        int INVALID_LEAUGE = -2;
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_LEAUGE,
                ua.preparingGamesSchedule("Champions League", "2019-2020"));
    }

    // 3.4 
    @Test
    @DisplayName("UserApplication_gameSchedule: Invalid Season")
    public void gameSchedule_Invalid_Season() {
        ua.LoginUser("David", "12345");
        int INVALID_SEASON = -1;
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_SEASON, ua.preparingGamesSchedule("La Liga", "2012-2013"));
    }

    //3.5
    @Test
    @DisplayName("UserApplication_gameSchedule: Invalid Privileges")
    public void gameSchedule_Invalid_Privileges() {
        ua.LoginUser("Ref1", "RefPassword1");
        int INVALID_PRIVILEGES = -9;
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_PRIVILEGES, ua.preparingGamesSchedule("La Liga", "2012-2013"));
    }

    // 3.6
    @Test
    @DisplayName("UserApplication_gameSchedule: Invalid TEAMS")
    public void gameSchedule_Invalid_TEAMS() {
        ua.LoginUser("Rep", "pass");
        int INVALID_TEAMS = -2;
        ArrayList<String> dates = new ArrayList<>();
        ArrayList<String> teamsIDS = new ArrayList<>();
        assertEquals(INVALID_TEAMS, ua.preparingGamesSchedule("UEFA", "2019-2020"));
    }
}
