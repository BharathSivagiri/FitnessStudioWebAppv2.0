package userDefinedPackages;

import java.util.regex.*;

public class StringException extends Exception
{
    public StringException(String message)
    {
        super(message);
    }
}
//class VerifyStringData
//{
//    String NAME_DISTRICT_PATTERN = "^[a-zA-Z]+\\s[a-zA-Z]+$";
//
//
//
//    //Function method to verify name....
//    public void setName(String name) throws StringException
//    {
//        Pattern pattern = Pattern.compile(NAME_DISTRICT_PATTERN);
//        if(name == null || name.trim().isEmpty() || !pattern.matcher(name).matches())
//        {
//            throw new StringException("Invalid name. Enter a valid name.");
//        }
//    }
//
//    //Function method to verify district....
//    public void setDistrict(String district) throws StringException
//    {
//        Pattern pattern = Pattern.compile(NAME_DISTRICT_PATTERN);
//        if(district == null || district.trim().isEmpty() || !pattern.matcher(district).matches())
//        {
//            throw new StringException("Invalid String. Enter a valid string.");
//        }
//    }
//}

