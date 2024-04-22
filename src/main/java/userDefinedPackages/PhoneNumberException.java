package userDefinedPackages;

import java.util.regex.*;

public class PhoneNumberException extends Exception
{
    public PhoneNumberException(String message) {
        super(message);
    }
}
//class VerifyNumberData
//{
//    String PHONE_NUMBER_PATTERN = "^[6-9]\\d{9}$";
//
//    public void setPhoneNumber(String phoneNumber) throws PhoneNumberException
//    {
//        Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
//        if (!pattern.matcher(phoneNumber).matches())
//        {
//            throw new PhoneNumberException("Invalid phone number.");
//        }
//
//    }
//}
