package userDefinedPackages;

import java.util.regex.*;

public class PasswordException extends Exception
{
    public PasswordException(String message)
    {
        super(message);
    }
}
//class VerifyPasswordData
//{
//    String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$])(?=\\S+$).{8,}$";
//
//    public void setPassword(String password) throws PasswordException
//    {
//        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
//        if (!pattern.matcher(password).matches())
//        {
//            throw new PasswordException("Invalid password input");
//        }
//    }
//}
