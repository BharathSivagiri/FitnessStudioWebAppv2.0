package FSWA;

import DatabaseConnect.DBConnection;
import userDefinedPackages.*;

import java.sql.SQLException;
import java.sql.*;
import java.util.*;
public class UserLoginPage extends UserLoginModeSelectionPage
{
    Scanner scan = new Scanner(System.in);
    DBConnection connect = new DBConnection();
   // UserLoginModeSelectionPage previous = new UserLoginModeSelectionPage();

//    public void loginCredentials() throws PasswordException
//    {
//        System.out.println("Login Initiated....");
//        System.out.print("Enter your userID: ");
//        String uID = scan.nextLine();
//
//        System.out.print("Enter your password: ");
//        String pword = scan.nextLine();
//        if(pword.equals("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$])(?=\\\\S+$).{8,}$"))
//        {
//            throw new PasswordException("Enter the correct password.....");
//        }
//
//        if (validateCredentials(uID, pword)) {
//            System.out.println("Access granted. Navigating to the next page...");
//            char validateID = uID.charAt(0);
//            if(validateID == 'A')
//            {
//                System.out.println("Logging in as admin...");
//                AdminDashboard admin = new AdminDashboard();
//                admin.DashBoardAdmin();
//            }
//            else
//            {
//                System.out.println("Logging in as customer...");
//                CustomerDashboard customer = new CustomerDashboard();
//                customer.DashBoardCustomer();
//            }
//        } else {
//            System.out.println("Invalid username or password. Please try again.");
//            UserLoginPage retry = new UserLoginPage();
//            retry.loginCredentials();
//        }
//    }
//    public boolean validateCredentials(String uID, String pword)
//    {
//        boolean isValid = false;
//        connect.InitiateDB(); // Ensure the database connection is initiated
//        {
//            try {
//                String sql = "SELECT COUNT(*) FROM UsersInfo WHERE userid = ? AND password = ?";
//                Object[] params = {uID, pword};
//                ResultSet resultSet = connect.PassDB(sql, params);
//                if (resultSet.next()) {
//                    int count = resultSet.getInt(1);
//                    isValid = count > 0;
//                }
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//            return isValid;
//        }
//    }
}
