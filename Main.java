import Service.UserApplication;

import java.util.Scanner;

import Domain.UserController;

public class Main {
    public static void main(String[] args) throws Exception {
        UserApplication up = new UserApplication();
        up.LoginUser("David", "12345");
        up.WhosISloggedIN();
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
