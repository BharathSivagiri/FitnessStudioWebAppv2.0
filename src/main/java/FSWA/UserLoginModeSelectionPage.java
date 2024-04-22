package FSWA;

import DatabaseConnect.DBConnection;
import userDefinedPackages.PasswordException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserLoginModeSelectionPage
{
//   UserLoginPage login = new UserLoginPage();
    Scanner scan = new Scanner(System.in);
    DBConnection connect = new DBConnection();
    public void LoginMode()
    {
            Scanner scan = new Scanner(System.in);
            String input;
            do {
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("---------------------------Fitness Studio Web Application--------------------------------");
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println(" ");
                System.out.println("User login mode....");
                System.out.println(" ");
                System.out.println("1. Login as Admin(Administrator)");
                System.out.println(" ");
                System.out.println("2. Login as Customer");
                System.out.println(" ");
                System.out.println("3. Previous page");
                System.out.println(" ");
                System.out.println("-----------------------------------------------------------------------------------------");

                System.out.print("Enter your choice : ");
                input = scan.next();

                switch (input)
                {
                    case "1":
                        System.out.println("Admin Login Initiated....");
                        Login();
                        // Add the code for Option 1 here
                        break;
                    case "2":
                        System.out.println("Customer Login Initiated....");
                        Login();
                        // Add the code for Option 2 here
                        break;
                    case "3":
                        System.out.println("Executing.......");
                        UserRegisterAndLoginPage previous = new UserRegisterAndLoginPage();
                        previous.RegisterandLogin();
                        break;
                    default:
                        System.out.println("Invalid selection. Please try again.");
                        break;
                }
            } while (!input.equals("3"));
    }
    public void Login()
    {
        Scanner scan = new Scanner(System.in);
        String input;
        do {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("---------------------------Fitness Studio Web Application--------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println("Login page....");
            System.out.println(" ");
            System.out.println("1. Initiate Login");
            System.out.println(" ");
            System.out.println("2. Previous page");
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------------------------");

            System.out.print("Enter your choice : ");
            input = scan.nextLine();

            switch (input)
            {
                case "1":
                    //UserLoginPage log = new UserLoginPage();
                    try {
                        loginCredentials();
                    } catch (PasswordException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "2":
                    System.out.println("Executing.......");
                    LoginMode();
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (!input.equals("5"));
    }
    public void loginCredentials() throws PasswordException
    {
        System.out.println("Login Initiated....");
        System.out.print("Enter your userID: ");
        String uID = scan.nextLine();

        System.out.print("Enter your password: ");
        String pword = scan.nextLine();
        if(pword.equals("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$])(?=\\\\S+$).{8,}$"))
        {
            throw new PasswordException("Enter the correct password.....");
        }

        if (validateCredentials(uID, pword)) {
            System.out.println("Access granted. Navigating to the next page...");
            char validateID = uID.charAt(0);
            if(validateID == 'A')
            {
                System.out.println("Logging in as admin...");
                AdminDashboard admin = new AdminDashboard();
                admin.DashBoardAdmin();
            }
            else
            {
                System.out.println("Logging in as customer...");
                CustomerDashboard customer = new CustomerDashboard();
                customer.DashBoardCustomer();
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
            UserLoginPage retry = new UserLoginPage();
            retry.loginCredentials();
        }
    }
    public boolean validateCredentials(String uID, String pword)
    {
        boolean isValid = false;
        connect.InitiateDB(); // Ensure the database connection is initiated
        {
            try {
                String sql = "SELECT COUNT(*) FROM UsersInfo WHERE userid = ? AND password = ?";
                Object[] params = {uID, pword};
                ResultSet resultSet = connect.PassDB(sql, params);
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    isValid = count > 0;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return isValid;
        }
    }
}

