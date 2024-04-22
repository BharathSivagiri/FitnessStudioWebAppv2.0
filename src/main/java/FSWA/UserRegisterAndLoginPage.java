package FSWA;

import DatabaseConnect.DBConnection;

import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import userDefinedPackages.*;

public class UserRegisterAndLoginPage extends FirstMainPage
{
    Scanner scan = new Scanner(System.in);
    DBConnection connect = new DBConnection();
   // UserRegisterAndLoginPage login = new UserRegisterAndLoginPage();
    UserLoginModeSelectionPage loginmode = new UserLoginModeSelectionPage();
//    FirstMainPage first = new FirstMainPage();
    public void RegisterandLogin()
    {
        String input;
        do {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("---------------------------Fitness Studio Web Application--------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println("User Registration and login....");
            System.out.println(" ");
            System.out.println("1. Registration");
            System.out.println(" ");
            System.out.println("2. Login");
            System.out.println(" ");
            System.out.println("3. Reset password");
            System.out.println(" ");
            System.out.println("4. Previous page");
            System.out.println(" ");
            System.out.println("-----------------------------------------------------------------------------------------");

            System.out.print("Enter your choice : ");
            input = scan.next();

            switch (input)
            {
                case "1":
                    try {
                        registerUser();
                    } catch (StringException | UsernameException | PhoneNumberException | PasswordException | EmailException e) {
                        throw new RuntimeException(e);
                    }
                    // Add the code for Option 1 here
                    break;
                case "2":
                    System.out.println("Login Initiated....");
                    loginmode.LoginMode();
                    // Add the code for Option 2 here
                    break;
                case "3":
                    resetPassword();
                    // Add the code for Option 3 here
                    break;
                case "4":
                    System.out.println("Returning.......");
//                    FirstMainPage();
                    return;
                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        } while (!input.equals("4"));
    }
    public void registerUser() throws StringException, UsernameException, PhoneNumberException, PasswordException, EmailException
    {
        String userId, name, uname, pword, district, email, gender, phonenumber, dateofbirth, address, pincode, profession;
        int level;

        System.out.println("Registration Initiated....");
        System.out.println(" ");


            System.out.print("Enter your User ID (Ex. A001 - Admin, C001 - Customer, T001 - Trainer, D001 - Doctor) : ");
            userId = scan.next();

            System.out.print("Enter Your Name [Note : The name should consist of two parts separated by a space. Each part should only contain alphabetic characters.]: ");
            name = scan.next();
            if(name.equals("^[a-zA-Z]+\\\\s[a-zA-Z]+$"))
            {
                throw new StringException("Enter the correct name.....");
            }


            System.out.print("Enter your username [Note : The username should only contain alphanumeric characters. It can be of any length.]: ");
            uname = scan.next();
            if(uname.equals("^[a-zA-Z0-9]*$"))
            {
                throw new UsernameException("Enter the correct username.....");
            }

            System.out.print("Enter your password [Note : 1. There should be at least one digit.\n" +
                    "2. There should be at least one lowercase letter.\n" +
                    "3. There should be at least one uppercase letter.\n" +
                    "4. There should be at least one special character among @, #, and $.\n" +
                    "5. There should be no whitespace.\n" +
                    "6. The password should be at least 8 characters long.] : ");
            pword = scan.next();
            if(pword.equals("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$])(?=\\\\S+$).{8,}$"))
            {
                throw new PasswordException("Enter the correct password.....");
            }

            System.out.print("Enter your district [Note : The name should consist of two parts separated by a space. Each part should only contain alphabetic characters.]: ");
            district = scan.next();
            if(district.equals("^[a-zA-Z]+\\\\s[a-zA-Z]+$"))
            {
                throw new StringException("Enter the correct district.....");
            }

            System.out.print("Enter your email address [Note : It should start with alphanumeric characters, underscore, hyphen, or plus sign. It can also contain periods (.) but they must not be the first or last character, and they cannot be consecutively used. It should start with alphanumeric characters or a hyphen followed by one or more periods and end with a top-level domain (like .com, .org) which is at least two characters long.]: ");
            email = scan.next();
            if(email.equals("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\" + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$"))
            {
                throw new EmailException("Enter the correct email address.....");
            }

            System.out.print("Enter your gender (type 'Male' or 'Female') : ");
            gender = scan.next();

            System.out.print("Enter your phonenumber [Note : The phone number should start with a digit between 6 and 9 and should be exactly 10 digits long.] : ");
            phonenumber = scan.next();
            if(phonenumber.equals("^[6-9]\\\\d{9}$"))
            {
                throw new PhoneNumberException("Enter the correct phone number.....");
            }

            System.out.print("Enter your level (1 - Admin, 2 - Customer, 3 - Trainer, 4 - Doctor) : ");
            level = scan.nextInt();

            System.out.print("Enter your DOB[Ex. DD-MM-YYYY] : ");
            dateofbirth = scan.next();

            System.out.print("Enter your Address : ");
            address = scan.next();

            System.out.print("Enter your pincode : ");
            pincode = scan.next();

            System.out.print("Enter your profession (Ex enter 'admin' if you are admin): ");
            profession = scan.next();
        try
        {
            connect.InitiateDB();

            String sql = "INSERT INTO UsersInfo (userid, name, username, password, district, email, gender, phonenumber, userlevel, dateofbirth, address, pincode, profession) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            Object[] params = {userId, name, uname, pword, district, email, gender, phonenumber, level, dateofbirth, address, pincode, profession};
            int rowsInserted = connect.InsertDB(sql,params);
            if (rowsInserted > 0)
            {
                System.out.println("A new user was inserted successfully!");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public void resetPassword()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Reset password Initiated....");
        System.out.println(" ");

        try {
            System.out.print("Enter the userID : ");
            String userID = scan.next();

            System.out.print("Enter your new password [Note : 1. There should be at least one digit.\n" +
                    "2. There should be at least one lowercase letter.\n" +
                    "3. There should be at least one uppercase letter.\n" +
                    "4. There should be at least one special character among @, #, and $.\n" +
                    "5. There should be no whitespace.\n" +
                    "6. The password should be at least 8 characters long.] : ");
            String newpassword = scan.next();
            if(newpassword.equals("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$])(?=\\\\S+$).{8,}$"))
            {
                throw new StringException("Enter the correct password.....");
            }

            connect.InitiateDB();

            String sql = "UPDATE UsersInfo SET password = ? WHERE userID = ?";
            Object[] params = {newpassword, userID};

            int rowsInserted = connect.UpdateDB(sql, params);
            if (rowsInserted > 0)
            {
                System.out.println("Password updated Successfully....");
            }
        } catch (SQLException | StringException e) {
            e.printStackTrace();
        }
    }

}

