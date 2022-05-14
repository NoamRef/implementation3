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

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UnitTests {
    UserController uc = new UserController();

    @Test
    @DisplayName("ControllerRegisterTest")
    public void TestUserRegister() {
        assertEquals(-1, uc.ResgisterUser("Test", "pass", "name", "Fan")); // user already exits
        assertEquals(-2, uc.ResgisterUser("NewRefrz", "pass", "name", "Refz")); // user already exits
        uc.DeleteUser("David");
        assertEquals(1, uc.ResgisterUser("David", "12345", "David", "Representative")); // user should be added

    }

}
