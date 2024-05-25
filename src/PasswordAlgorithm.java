import java.util.Random;

public class PasswordAlgorithm {
    String generate(boolean[] elementsToAdd, int passwordLength)
    {
        String[] A_Z = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String[] _0_9 = {"0","1","2","3","4","5","6","7","8","9"};
        String[] specials = {"!","@","#","$","%","^","&","*","(",")"};

        Random random = new Random();
        int randElement;
        int randChar;
        StringBuilder generatedPassword= new StringBuilder();

        for(int i = 0 ; i < passwordLength ; i++)
        {
            randElement = random.nextInt(4);
            if(randElement == 0 && elementsToAdd[0])
            {
                randChar = random.nextInt(26);
                generatedPassword.append(A_Z[randChar]);
            }
                else if(randElement == 1 && elementsToAdd[1])
                {
                    randChar = random.nextInt(26);
                    generatedPassword.append(A_Z[randChar].toLowerCase());
                }
                    else if(randElement == 2 && elementsToAdd[2])
                    {
                        randChar = random.nextInt(10);
                        generatedPassword.append(_0_9[randChar]);
                    }
                        else if(randElement == 3 && elementsToAdd[3])
                        {
                            randChar = random.nextInt(10);
                            generatedPassword.append(specials[randChar]);
                        }
                            else
                            {
                                i--;
                            }
        }

        return generatedPassword.toString();
    }
}
