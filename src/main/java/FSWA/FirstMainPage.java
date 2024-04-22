package FSWA;

import java.util.*;

public class FirstMainPage
{
    public static void main(String[] args)
    {
        UserRegisterAndLoginPage reg = new UserRegisterAndLoginPage();
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("---------------------------Fitness Studio Web Application--------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println("Welcome. Enter the specified options below to proceed....");
            System.out.println(" ");
            System.out.println("1. Admin");
            System.out.println(" ");
            System.out.println("2. User");
            System.out.println(" ");
            System.out.println("3. Exit Application");
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------------------------");

            System.out.print("Enter your choice : ");
            input = scan.next();


            switch (input)
            {
                case "1":
                    System.out.println("Admin preveliges Initiated....");
                    reg.RegisterandLogin();
                    // Add the code for Option 1 here
                    break;
                case "2":
                    System.out.println("User preveliges Initiated....");
                    reg.RegisterandLogin();
                    // Add the code for Option 2 here
                    break;
                case "3":
                    System.out.println("Exiting... Have a nice day.......");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (!input.equals("3"));
    }
    public void MainPage()
    {

    }
}

