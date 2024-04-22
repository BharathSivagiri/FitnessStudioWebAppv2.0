package userDefinedPackages;

import java.util.regex.*;

public class UsernameException extends Exception
{
    public UsernameException(String message)
    {
        super(message);
    }
}

//class VerifyUsernameData
//{
//    String USERNAME_PATTERN = "^[a-zA-Z0-9]*$";
//
//    public void setUsername(String username) throws UsernameException
//    {
//        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
//        if (!pattern.matcher(username).matches())
//        {
//            throw new UsernameException("Invalid username. Enter the correct username........");
//        }
//    }
//}
