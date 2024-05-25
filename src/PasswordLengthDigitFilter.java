import static java.lang.Character.isDigit;

public class PasswordLengthDigitFilter
{
    String check(String passwordLength)
    {
        char[] passwordLengthArray = passwordLength.toCharArray();
        StringBuilder passwordLengthChecked;
        passwordLengthChecked = new StringBuilder();

        for(char ch : passwordLengthArray)
        {
            if(isDigit(ch))
            {
                passwordLengthChecked.append(ch);
            }
        }

        return String.valueOf(passwordLengthChecked);
    }
}
