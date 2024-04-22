package FSWA;
import DatabaseConnect.DBConnection;
import userDefinedPackages.*;

import java.awt.*;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
public class AdminDashboard {
    Scanner scan = new Scanner(System.in);
    DBConnection connect = new DBConnection();
    UserRegisterAndLoginPage addusers = new UserRegisterAndLoginPage();
    UserLoginPage login = new UserLoginPage();

    public void DashBoardAdmin() {
        String input;
        do {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("---------------------------Fitness Studio Web Application--------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println("Admin Dashboard....");
            System.out.println(" ");
            System.out.println("1. Trainers Managemant");
            System.out.println(" ");
            System.out.println("2. Doctors Management");
            System.out.println(" ");
            System.out.println("3. Training Management");
            System.out.println(" ");
            System.out.println("4. Customers Management(View only)");
            System.out.println(" ");
            System.out.println("5. Search (By userID and Profession)");
            System.out.println(" ");
            System.out.println("6. Payment Management (View only)");
            System.out.println(" ");
            System.out.println("7. Report Generation");
            System.out.println(" ");
            System.out.println("8. Logout(redirects to previous page)...");
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------------------------");

            System.out.print("Enter your choice : ");
            input = scan.next();

            switch (input) {
                case "1":
                    //opens trainers management module
                    TrainersCRUD();
                    // Add the code for Option 1 here
                    break;
                case "2":
                    //opens doctor management module
                    DoctorsCRUD();
                    // Add the code for Option 2 here
                    break;
                case "3":
                    //opens training module
                    TrainingCRUD();
                    // Add the code for Option 3 here
                    break;
                case "4":
                    //opens customers management module(View only)
                    CustomersView();
                    break;
                case "5":
                    //opens search module
                    SearchUsers();
                    break;
                case "6":
                    //opens payment module
                    ViewPaymentDetails();
                    break;
                case "7":
                    //Generates the report
                    UserReportGeneration();
                    break;
                case "8":
                    //redirects to previous page
                    System.out.println("Logged out successfully....");
                    login.Login();
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (!input.equals("8"));
    }

    public void TrainersCRUD() {
        String input;
        do {
            System.out.println("Enter your choices below....");
            System.out.println("1. Add trainer details(Give 'T001' as userID for trainers)...");
            System.out.println("2. Update trainer details...");
            System.out.println("3. View trainer details...");
            System.out.println("4. Delete trainer details...");
            System.out.println("5. Back....");

            System.out.print("Enter your operation : ");
            input = scan.next();

            switch (input) {
                case "1":
                    //Adding the trainer details
                    try {
                        addusers.registerUser();
                    } catch (StringException | UsernameException | PhoneNumberException | PasswordException | EmailException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "2":
                    //Updating the trainer details
                    UpdateTrainerDetails();
                    break;
                case "3":
                    //View the trainer details
                    ViewTrainerDetails();
                    break;
                case "4":
                    //Delete the trainer details
                    DeleteTrainerDetails();
                    break;
                case "5":
                    //back to main switch function.
                    DashBoardAdmin();
                    break;
                default:
                    System.out.println("Wrong option. Try again.......");
                    break;
            }
        } while (!input.equals("5"));
    }

    public void DoctorsCRUD() {
        String input;
        do {
            System.out.println("Enter your choices below....");
            System.out.println("1. Add doctor details...");
            System.out.println("2. Update doctor details...");
            System.out.println("3. View doctor details...");
            System.out.println("4. Delete doctor details...");
            System.out.println("5. Back....");

            System.out.println("Enter your operation : ");
            input = scan.next();
            switch (input) {
                case "1":
                    try {
                        addusers.registerUser();
                    }catch (StringException | UsernameException | PhoneNumberException | PasswordException | EmailException e) {
                        throw new RuntimeException(e);
                    }
                    //Adding the doctor details
                    break;
                case "2":
                    //Updating the doctor details
                    UpdateDoctorDetails();
                    break;
                case "3":
                    //View the doctor details
                    ViewDoctorDetails();
                    break;
                case "4":
                    //Delete the doctor details
                    DeleteDoctorDetails();
                    break;
                case "5":
                    //back to main switch function.
                    DashBoardAdmin();
                    break;
                default:
                    System.out.println("Wrong option. Try again.......");
                    break;
            }
        } while (!input.equals("5"));
    }

    public void TrainingCRUD() {
        String input;
        do {
            System.out.println("Enter your choices below....");
            System.out.println("1. Add class and time...");
            System.out.println("2. Update class and time...");
            System.out.println("3. View class and time...");
            System.out.println("4. Delete class and time...");
            System.out.println("5. Back....");

            System.out.print("Enter your operation : ");
            input = scan.next();

            switch (input) {
                case "1":
                    AddingClassDetails();
                    //Adding class and time details
                    break;
                case "2":
                    UpdateClassDetails();
                    //Updating the class and time details
                    break;
                case "3":
                    ViewClassDetails();
                    //View the class and time details
                    break;
                case "4":
                    DeleteClassDetails();
                    //Delete the class and time details
                    break;
                case "5":
                    //back to main switch function.
                    DashBoardAdmin();
                    break;
                default:
                    System.out.println("Wrong option. Try again.......");
                    break;
            }
        } while (!input.equals("5"));
    }

    public void CustomersView() {
        String input;
        System.out.println("View the customer details......");
        do {
            System.out.println("1. Search by userID");
            System.out.println("2. Back.....");
            input = scan.next();

            switch (input) {
                case "1":
                    try {
                        System.out.print("Enter the customer userID : ");
                        String cousID = scan.next();

                        connect.InitiateDB();

                        String sql = "SELECT * FROM UsersInfo WHERE userID = ?";
                        Object[] params = {cousID};
                        ResultSet rs = connect.ReadDB(sql, params);

                        if (rs.next()) {
                            do {
                                // Process the row
                                System.out.println("User ID : " + rs.getString("userID"));
                                System.out.println("User Name : " + rs.getString("name"));
                                System.out.println("User username : " + rs.getString("username"));
                                System.out.println("User Email : " + rs.getString("email"));
                                System.out.println("User address : " + rs.getString("address"));
                                System.out.println("User profession : " + rs.getString("profession"));

                                System.out.println("Details fetched successfully....");
                            } while (rs.next());
                        } else {
                            System.out.println("No user found with the given userID.");
                        }
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    DashBoardAdmin();
                    break;
                default:
                    System.out.println("Wrong option. Try Again....");
                    break;
            }
        } while (!input.equals("2"));
    }

    public void SearchUsers() {
        String input;
        System.out.println("View the customer details......");
        do {
            System.out.println("1. Search by userID");
            System.out.println("2. Search by profession.....");
            System.out.println("3. Back.....");

            System.out.print("Enter the operation : ");
            input = scan.next();

            switch (input) {
                case "1":
                    try {
                        System.out.print("Enter the userID : ");
                        String uID = scan.next();

                        connect.InitiateDB();

                        String sql = "SELECT * FROM UsersInfo WHERE userID = ?";
                        Object[] params = {uID};
                        ResultSet rs = connect.ReadDB(sql, params);

                        if (rs.next()) {
                            do {
                                // Process the row
                                System.out.println("User ID : " + rs.getString("userID"));
                                System.out.println("User Name : " + rs.getString("name"));
                                System.out.println("User username : " + rs.getString("username"));
                                System.out.println("User Email : " + rs.getString("email"));
                                System.out.println("User address : " + rs.getString("address"));
                                System.out.println("User profession : " + rs.getString("profession"));

                                System.out.println("Details fetched successfully....");
                            } while (rs.next());
                        } else {
                            System.out.println("No user found with the given userID.");
                        }
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter the profession : ");
                        String prof = scan.next();

                        connect.InitiateDB();

                        String sql = "SELECT * FROM UsersInfo WHERE profession = ?";
                        Object[] params = {prof};
                        ResultSet rs = connect.ReadDB(sql, params);

                        if (rs.next()) {
                            do {
                                // Process the row
                                System.out.println("User ID : " + rs.getString("userID"));
                                System.out.println("User Name : " + rs.getString("name"));
                                System.out.println("User username : " + rs.getString("username"));
                                System.out.println("User Email : " + rs.getString("email"));
                                System.out.println("User address : " + rs.getString("address"));
                                System.out.println("User profession : " + rs.getString("profession"));

                                System.out.println("Details fetched successfully....");
                            } while (rs.next());
                        } else {
                            System.out.println("No user found with the given userID.");
                        }
                        rs.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    //back to search function
                    DashBoardAdmin();
                    break;
                default:
                    System.out.println("Enter the correct choice......");
                    break;
            }
            break;
        } while (!input.equals("3"));
    }

    public void UpdateTrainerDetails() {
        String inputs;
        do {

            System.out.println("Details that can be updated are listed below ");
            System.out.println("1. Update Username");
            System.out.println("2. Update Email");
            System.out.println("3. Update phonenumber");
            System.out.println("4. Update address");
            System.out.println("5. Back....");

            System.out.print("Enter your choice : ");
            inputs = scan.next();

            switch (inputs) {
                case "1":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new username [Note : The username should only contain alphanumeric characters. It can be of any length.]: ");
                        String newUsername = scan.next();
                        if(newUsername.equals("^[a-zA-Z0-9]*$"))
                        {
                            throw new UsernameException("Enter the correct username.....");
                        }

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET username = ? WHERE userID = ?";
                        Object[] params = {newUsername, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Username updated Successfully....");
                        }
                    } catch (SQLException | UsernameException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new Email [Note : It should start with alphanumeric characters, underscore, hyphen, or plus sign. It can also contain periods (.) but they must not be the first or last character, and they cannot be consecutively used. It should start with alphanumeric characters or a hyphen followed by one or more periods and end with a top-level domain (like .com, .org) which is at least two characters long.] : ");
                        String newEmail = scan.next();
                        if(newEmail.equals("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\" + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$"))
                        {
                            throw new EmailException("Enter the correct email address.....");
                        }

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET email = ? WHERE userID = ?";
                        Object[] params = {newEmail, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Email updated Successfully....");
                        }
                    } catch (SQLException | EmailException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new phonenumber [Note : The phone number should start with a digit between 6 and 9 and should be exactly 10 digits long.]: ");
                        String newpnumber = scan.next();
                        if(newpnumber.equals("^[6-9]\\\\d{9}$"))
                        {
                            throw new PhoneNumberException("Enter the correct phone number.....");
                        }

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET phonenumber = ? WHERE userID = ?";
                        Object[] params = {newpnumber, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Phonenumber updated Successfully....");
                        }
                    } catch (SQLException | PhoneNumberException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new address : ");
                        String newAddress = scan.next();

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET address = ? WHERE userID = ?";
                        Object[] params = {newAddress, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Address updated Successfully....");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    System.out.println("Returning.....");
                    break;
                default:
                    System.out.println("Enter the correct choice....");
                    break;
            }
        } while (!inputs.equals("5"));
    }

    public void ViewTrainerDetails() {
        try {
            System.out.print("Enter the trainer's userID : ");
            String uID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM UsersInfo WHERE userID = ?";
            Object[] params = {uID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("User ID : " + rs.getString("userID"));
                    System.out.println("User Name : " + rs.getString("name"));
                    System.out.println("User username : " + rs.getString("username"));
                    System.out.println("User Email : " + rs.getString("email"));
                    System.out.println("User address : " + rs.getString("address"));
                    System.out.println("User profession : " + rs.getString("profession"));

                    System.out.println("Details fetched successfully....");
                } while (rs.next());
            } else {
                System.out.println("No trainer found with the given userID.");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteTrainerDetails() {
        try {
            System.out.print("Enter the UserID : ");
            String userID = scan.next();

            connect.InitiateDB();

            String sql = "DELETE FROM UsersInfo WHERE UserID = ?";
            Object[] params = {userID};

            int rowsDeleted = connect.DeleteDB(sql, params);
            if (rowsDeleted > 0) {
                System.out.println("User Deleted Successfully...");
            } else {
                System.out.println("User not found....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void UpdateDoctorDetails() {
        String inputs;
        do {
            System.out.println("Details that can be updated are listed below ");
            System.out.println("1. Update Username");
            System.out.println("2. Update Email");
            System.out.println("3. Update phonenumber");
            System.out.println("4. Update address");
            System.out.println("5. Back....");

            System.out.print("Enter your choice : ");
            inputs = scan.next();

            switch (inputs) {
                case "1":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new username [Note : The username should only contain alphanumeric characters. It can be of any length.]: ");
                        String newUsername = scan.next();
                        if(newUsername.equals("^[a-zA-Z0-9]*$"))
                        {
                            throw new UsernameException("Enter the correct username.....");
                        }

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET username = ? WHERE userID = ?";
                        Object[] params = {newUsername, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Username updated Successfully....");
                        }
                    } catch (SQLException | UsernameException e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new Email [Note : It should start with alphanumeric characters, underscore, hyphen, or plus sign. It can also contain periods (.) but they must not be the first or last character, and they cannot be consecutively used. It should start with alphanumeric characters or a hyphen followed by one or more periods and end with a top-level domain (like .com, .org) which is at least two characters long.]: ");
                        String newEmail = scan.next();
                        if(newEmail.equals("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\" + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$"))
                        {
                            throw new EmailException("Enter the correct email address.....");
                        }

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET email = ? WHERE userID = ?";
                        Object[] params = {newEmail, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Email updated Successfully....");
                        }
                    } catch (SQLException | EmailException e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new phonenumber [Note : The phone number should start with a digit between 6 and 9 and should be exactly 10 digits long.]: ");
                        String newpnumber = scan.next();
                        if(newpnumber.equals("^[6-9]\\\\d{9}$"))
                        {
                            throw new PhoneNumberException("Enter the correct phone number.....");
                        }

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET phonenumber = ? WHERE userID = ?";
                        Object[] params = {newpnumber, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Phonenumber updated Successfully....");
                        }
                    } catch (SQLException | PhoneNumberException e) {
                        e.printStackTrace();
                    }
                    break;
                case "4":
                    try {
                        System.out.print("Enter the userID : ");
                        String userID = scan.next();
                        System.out.print("Enter your new address : ");
                        String newAddress = scan.next();

                        connect.InitiateDB();

                        String sql = "UPDATE UsersInfo SET address = ? WHERE userID = ?";
                        Object[] params = {newAddress, userID};

                        int rowsInserted = connect.UpdateDB(sql, params);
                        if (rowsInserted > 0) {
                            System.out.println("Address updated Successfully....");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "5":
                    System.out.println("Returning.....");
                    break;
                default:
                    System.out.println("Enter the correct choice....");
                    break;
            }
        } while (!inputs.equals("5"));
    }

    public void ViewDoctorDetails() {
        try {
            System.out.print("Enter the doctor's userID : ");
            String uID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM UsersInfo WHERE userID = ?";
            Object[] params = {uID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("User ID : " + rs.getString("userID"));
                    System.out.println("User Name : " + rs.getString("name"));
                    System.out.println("User username : " + rs.getString("username"));
                    System.out.println("User Email : " + rs.getString("email"));
                    System.out.println("User address : " + rs.getString("address"));
                    System.out.println("User profession : " + rs.getString("profession"));

                    System.out.println("Details fetched successfully....");
                } while (rs.next());
            } else {
                System.out.println("No doctor found with the given userID.");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteDoctorDetails() {
        try {
            System.out.print("Enter the UserID : ");
            String userID = scan.next();

            connect.InitiateDB();

            String sql = "DELETE FROM UsersInfo WHERE UserID = ?";
            Object[] params = {userID};

            int rowsDeleted = connect.DeleteDB(sql, params);
            if (rowsDeleted > 0) {
                System.out.println("User Deleted Successfully...");
            } else {
                System.out.println("User not found....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void AddingClassDetails() {
        try {
            System.out.print("Adding class details....");
            System.out.println(" ");

            System.out.print("Enter the classID(Ex - CS001) : ");
            String classID = scan.next();
            System.out.print("Enter the class name : ");
            String className = scan.next();
            System.out.print("Enter the start time : ");
            String startTime = scan.next();
            System.out.print("Enter the end time : ");
            String endTime = scan.next();
            System.out.print("Enter the start day : ");
            String startDay = scan.next();
            System.out.print("Enter the end day : ");
            String endDay = scan.next();
            System.out.print("Enter the cost : ");
            String cost = scan.next();

            connect.InitiateDB();

            String sql = "INSERT INTO ClassInfo (classID, classname, classstarttime, classendtime, classstartday, classendday, subscriptioncost) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {classID, className, startTime, endTime, startDay, endDay, cost};
            int rowsInserted = connect.InsertDB(sql, params);
            if (rowsInserted > 0) {
                System.out.println("A new class was inserted successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateClassDetails() {
        try {
            System.out.print("Enter the classID : ");
            String classID = scan.next();
            System.out.print("Enter the new starting time for class : ");
            String STnew = scan.next();
            System.out.print("Enter the new ending time for the class : ");
            String ETnew = scan.next();

            connect.InitiateDB();

            String sql = "UPDATE ClassInfo SET classstarttime = ?  AND classendtime = ? WHERE classID = ?";
            Object[] params = {STnew, ETnew, classID};

            int rowsInserted = connect.UpdateDB(sql, params);
            if (rowsInserted > 0) {
                System.out.println("Class updated Successfully....");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ViewClassDetails() {
        try {
            System.out.print("Enter the classID : ");
            String classID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM ClassInfo WHERE classID = ?";
            Object[] params = {classID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("Class Name : " + rs.getString("classname"));
                    System.out.println("Class Start Time : " + rs.getString("classstarttime"));
                    System.out.println("Class End Time : " + rs.getString("classendtime"));
                    System.out.println("Class Start Day : " + rs.getString("classstartday"));
                    System.out.println("Class End Day : " + rs.getString("classendday"));

                    System.out.println("Details fetched successfully....");
                } while (rs.next());
            } else {
                System.out.println("No class found with the given classID.");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteClassDetails() {
        try {
            System.out.print("Enter the classID : ");
            String classID = scan.next();

            connect.InitiateDB();

            String sql = "DELETE FROM ClassInfo WHERE classID = ?";
            Object[] params = {classID};

            int rowsDeleted = connect.DeleteDB(sql, params);
            if (rowsDeleted > 0) {
                System.out.println("Class Deleted Successfully...");
            } else {
                System.out.println("Class not found....");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ViewPaymentDetails() {
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
    public void CustomerSubscriptionDetails()
    {
        try {
            System.out.print("Enter the userID : ");
            String uID = scan.next();

            connect.InitiateDB();

            String sql = "SELECT * FROM SubscriptionInfo, WHERE userID = ?";
            Object[] params = {uID};
            ResultSet rs = connect.ReadDB(sql, params);

            if (rs.next()) {
                do {
                    // Process the row
                    System.out.println("User ID : " + rs.getString("userID"));
                    System.out.println("Class ID : " + rs.getString("classID"));
                    System.out.println("Payment Status : " + rs.getString("paidstatus"));

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
    public void UserReportGeneration()
    {
        String input;
        do {
            System.out.println("Enter your choices below to view the report....");
            System.out.println("1. Doctor report...");
            System.out.println("2. Trainer report...");
            System.out.println("3. Customer report...");
            System.out.println("4. Subscription report...");
            System.out.println("5. Back....");

            System.out.print("Enter your operation : ");
            input = scan.next();

            switch (input) {
                case "1":
                    //views the details of the doctor
                    ViewDoctorDetails();
                    break;
                case "2":
                    //views the details of the trainer
                    ViewTrainerDetails();
                    break;
                case "3":
                    //views the details of the customer
                    CustomersView();
                    break;
                case "4":
                    //views the details of customer subscription
                    CustomerSubscriptionDetails();
                    break;
                case "5":
                    DashBoardAdmin();
                    //back to main switch function.
                    break;
                default:
                    System.out.println("Wrong option. Try again.......");
                    break;
            }
        } while (!input.equals("5"));
    }
}

