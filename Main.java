import Service.UserApplication;

import java.util.ArrayList;
import java.util.Scanner;

import Domain.UserController;

public class Main {
    public static void main(String[] args) throws Exception {
        UserApplication up = new UserApplication();
        String[] str = new String[2];
        str[0] = "RefName1";
        str[1] = "RefName2";
        up.RefreePlacement("2020-2021", "La Liga", str);
        up.LoginUser("David", "12345");
        up.LoginUser("David", "12345");
        up.LogOut();
        up.LoginUser("Rep", "pass"); // works
        up.RefreePlacement("2020-2021", "La Liga", str);
        up.RefreePlacement("2020-2021", "La Lga", str);
        str[0] = "RefName3";
        up.RefreePlacement("2020-2021", "La Liga", str);
        up.RefreePlacement("2021-2022", "La Liga", str);
        
        //Use case 3
        // up.AddTeam("FC Barcelona", 1,4);
        // up.AddTeam("Sevilla FC", 1,4);
        // up.AddTeam("Real Sociedad", 1,4);
        // up.AddTeam("Athletic Bilbao", 1,4);
        // up.AddTeam("Valencia CF", 1,4);
        // up.AddTeam("Osasuna", 1,4);
        // up.AddTeam("Espanyol", 1,4);
        // up.createGameSchedule();
        // ArrayList<String> levels = new ArrayList<>();
        // levels.add("Quarter-Final");
        // levels.add("Semi-Final");
        // levels.add("Final");
        // ArrayList<String> dates= new ArrayList<>();
        // dates.add("01/01/2023 21:00");
        // dates.add("08/01/2023 21:00");
        // dates.add("15/01/2023 21:00");
        // up.preparingGamesSchedule("La Liga","2020-2021","1","4",levels, dates);
        
        /**
         * UserApplication up = new UserApplication();
         * Scanner scanner = new Scanner(System.in);
         * String choice = "-1";
         * System.out.println("Choose:\n1-Register\n2-Login\n0-Exit");
         * while (!choice.equals("0")) {
         * choice = scanner.nextLine();
         * if (choice.equals("1")) {
         * up.ResgisterUser();
         * }
         * System.out.println("choice: " + choice);
         * }
         * scanner.close();
         */
    }
}
