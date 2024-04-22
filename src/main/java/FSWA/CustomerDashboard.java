package FSWA;
import DatabaseConnect.DBConnection;

import java.sql.SQLException;
import java.sql.*;
import java.util.*;
public class CustomerDashboard implements CardDetails
{
    Scanner scan = new Scanner(System.in);
    DBConnection connect = new DBConnection();

    UserLoginPage login = new UserLoginPage();
    public void DashBoardCustomer()
    {
        String input;
        do{
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("---------------------------Fitness Studio Web Application--------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println("Customer Dashboard....");
            System.out.println(" ");
            System.out.println("1. Training Managemant");
            System.out.println(" ");
            System.out.println("2. Payment Management[Only for users who pay through credit or debit card]");
            System.out.println(" ");
            System.out.println("3. Logout(redirects to previous page)");
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------------------------");

            System.out.print("Enter your choice : ");
            input = scan.next();

            switch (input)
            {
                case "1":
                    //opens training management module
                    TrainingCRUD();
                    break;
                case "2":
                    //opens payment management module
                    CardDetailsCRUD();
                    // Add the code for Option 2 here
                    break;
                case "3":
                    //redirects to previous page
                    System.out.println("Logged out successfully....");
                    login.Login();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }while(!input.equals("3"));
    }
    public void TrainingCRUD()
    {
        System.out.println("Subscription Details.......");
        System.out.println(" ");

        String input;
        do{
            System.out.println("Enter your choices below....");
            System.out.println("1. Add Subscriptions...");
            System.out.println("2. Update Subscriptions...");
            System.out.println("3. View Subscriptions...");
            System.out.println("4. Delete Subscriptions...");
            System.out.println("5. View Time slots...");
            System.out.println("6. Back....");

            System.out.print("Enter your operation : ");
            input = scan.next();

            switch (input)
            {
                case "1":
                    AddSubscriptionDetails();
                    //Adding class and time details
                    break;
                case "2":
                    UpdateSubscriptionDetails();
                    //Updating the class and time details
                    break;
                case "3":
                    ViewSubscriptionDetails();
                    //View the class and time details
                    break;
                case "4":
                    DeleteSubscriptionDetails();
                    //Delete the class and time details
                    break;
                case "5":
                    ViewTimeSlotsDetails();
                    break;
                case "6":
                    //back to main switch function.
                    DashBoardCustomer();
                    break;
                default:
                    System.out.println("Wrong option. Try again.......");
                    break;
            }
        }while(!input.equals("6"));
    }
    public void AddSubscriptionDetails()
    {
        System.out.println("Subscription addition menu....");
        System.out.println(" ");

        try{
            System.out.print("Enter the classes' classID you want to subscribe : ");
            String classID = scan.next();
            System.out.print("Enter your userID : ");
            String userID = scan.next();
            System.out.print("Enter your preferred timeslot[Ex. 12PM-1PM] : ");
            String preferredTimeslot = scan.next();
            System.out.print("Enter your payment method : ");
            String paymentMethod = scan.next();
            System.out.print("Enter your card expiry date : ");
            String cardExpiryDate = scan.next();
            System.out.print("Enter your CVV number : ");
            String cardVendor = scan.next();
            System.out.print("Enter your card number[if you paid via credit or debit card] : ");
            String cardNumber = scan.next();
            System.out.print("Enter the cost of the subscription : ");
            String cost = scan.next();
//            System.out.print("Are you sure to pay the subscription amount[Y/N] : ");
//            String choice = scan.next();
            System.out.print("Enter the payment status [Paid - P, Notpaid - NP] : ");
            String status = scan.next();
            System.out.print("Enter the paymentID : ");
            String paymentID = scan.next();
//            UUID paymentID = UUID.randomUUID();
//            String status;
//            if (choice.equalsIgnoreCase("Y"))
//            {
//                status = "P";
//            }
//            else
//            {
//                status = "NP";
//            }

            connect.InitiateDB();

            String sql = "INSERT INTO SubscriptionInfo (classID, paymentID, userID, timeslot, paymentsmode, cardnumber, subscriptioncost, paidstatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {classID, paymentID, userID, preferredTimeslot, paymentMethod, cardNumber,cost, status};
            int rowsInserted = connect.InsertDB(sql,params);
            if (rowsInserted > 0)
            {
                System.out.println("A new subscription was added successfully!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdateSubscriptionDetails()
    {
        String inputs;
        do{
            System.out.println("Following parameters can be updated..");
            System.out.println(" ");

            System.out.println("1. Update timeslot..");
            System.out.println("2. Update payment status..");
            System.out.println("3. Back...");

            System.out.print("Enter your choice : ");
            inputs = scan.next();

            switch (inputs)
            {
                case "1":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();

                        System.out.print("Enter your new timeslot[Ex. 12PM-1PM] : ");
                        String newtime = scan.next();

                        connect.InitiateDB();

                        String sql = "UPDATE SubscriptionInfo SET timeslot = ? WHERE userID = ?";
                        Object[] params = {newtime, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0)
                        {
                            System.out.println("Timeslot updated Successfully....");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();

                        System.out.print("Enter the payment status [Paid - P, Notpaid - NP] : ");
                        String status = scan.next();

                        connect.InitiateDB();

                        String sql = "UPDATE SubscriptionInfo SET paidstatus = ? WHERE userID = ?";
                        Object[] params = {status ,userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0)
                        {
                            System.out.println("Payment status updated Successfully....");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    DashBoardCustomer();
                    break;
                default:
                    //code here
                    System.out.println("Enter the correct choice....");
                    break;
            }
        }while(!inputs.equals("3"));
    }
    public void ViewSubscriptionDetails()
    {
        try {
            System.out.print("Enter the userID : ");
            String uID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM SubscriptionInfo WHERE userID = ?";
            Object[] params = {uID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("User ID : " + rs.getString("userID"));
                    System.out.println("Class ID : " + rs.getString("classID"));
                    System.out.println("Payment Status : " + rs.getString("paidstatus"));
                    System.out.println("Payment ID : " + rs.getString("paymentID"));

                    System.out.println("Details fetched successfully....");
                } while (rs.next());
            } else {
                System.out.println("No subscription found with the given userID.");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void DeleteSubscriptionDetails()
    {
        try {
            System.out.print("Enter the Payment ID : ");
            String payID = scan.next();

            connect.InitiateDB();

            String sql = "DELETE FROM SubscriptionInfo WHERE paymentID = ?";
            Object[] params = {payID};

            int rowsDeleted = connect.DeleteDB(sql, params);
            if (rowsDeleted > 0)
            {
                System.out.println("Subscription Deleted Successfully...");
            }
            else
            {
                System.out.println("Subscription not found....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void ViewTimeSlotsDetails()
    {
        try {
            System.out.print("Enter the classID : ");
            String classID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM SubscriptionInfo WHERE classID = ?";
            Object[] params = {classID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("User ID : " + rs.getString("userID"));
                    System.out.println("Class ID : " + rs.getString("classID"));
                    System.out.println("Timeslot : " + rs.getString("timeslot"));

                    System.out.println("Details fetched successfully....");
                } while (rs.next());
            } else {
                System.out.println("No timeslots found with the given userID.");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void CardDetailsCRUD()
    {
        System.out.println("Payment Details[Only for users who pay through credit or debit card].......");
        System.out.println(" ");

        String input;
        do{
            System.out.println("Enter your choices below....");
            System.out.println("1. Update Payment details...");
            System.out.println("2. View Payment details...");
            System.out.println("3. Delete Payment details...");
            System.out.println("4. Back....");

            System.out.print("Enter your operation : ");
            input = scan.next();

            switch (input)
            {
                case "1":
                    UpdateCardDetails();
                    break;
                case "2":
                    ViewCardDetails();
                    break;
                case "3":
                    DeleteCardDetails();
                    break;
                case "4":
                    DashBoardCustomer();
                    break;
                default:
                    System.out.println("Wrong option. Try again.......");
                    break;
            }
        }while(!input.equals("4"));
    }
    public void UpdateCardDetails()
    {
        try {
            System.out.print("Enter the userID : ");
            String userID = scan.next();
            System.out.print("Enter your new card number : ");
            String cardNumber = scan.next();

            connect.InitiateDB();

            String sql = "UPDATE ClassInfo SET cardnumber = ? WHERE userid = ?";
            Object[] params = {cardNumber, userID};

            int rowsInserted = connect.UpdateDB(sql, params);
            if (rowsInserted > 0)
            {
                System.out.println("Details updated Successfully....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void ViewCardDetails()
    {
        try{
            System.out.print("Enter the userID : ");
            String userID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM ClassInfo WHERE userid = ?";
            Object[] params = {userID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("User ID : " + rs.getString("userid"));
                    System.out.println("Payment ID : " + rs.getString("paymentID"));
                    System.out.println("Card number : " + rs.getString("cardnumber"));
                    System.out.println("Payment mode : " + rs.getString("paymentsmode"));

                    System.out.println("Details fetched successfully....");
                } while(rs.next());
            } else {
                System.out.println("No details found with the given userID.");
            }
            rs.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void DeleteCardDetails()
    {
        try {
            System.out.print("Enter the userID : ");
            String classID = scan.next();

            connect.InitiateDB();

            String sql = "DELETE FROM ClassInfo WHERE userid = ?";
            Object[] params = {classID};

            int rowsDeleted = connect.DeleteDB(sql, params);
            if (rowsDeleted > 0)
            {
                System.out.println("Card details deleted Successfully...");
            }
            else
            {
                System.out.println("Card details not found....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
