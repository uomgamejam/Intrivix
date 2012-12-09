/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author georgevanburgh
 */

public class Hiscores {

    private final static String HISCORE_PATH = "./src/Intrivix/game/assets/hiscore";
    
    /**
     *
     * @return
     */
    public static String[] buildScoreArray() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(HISCORE_PATH));
        while(input.hasNextLine())
        { 
                for(int index = 0; index < 10; index++)
                {
                    String [] row = input.nextLine().split("\\s+");
                    hiscoreArray[index] = row [indexTwo];
                }
            for(int indexThree = 0; indexThree < 10; indexThree++)
            {
                for(int indexFour = 0; indexFour < 3; indexFour++)
                {
                    System.out.println(hiscoreArray[indexFour]);
                }
            }
        }
        
        return null;
    }
    
    /**
     * @param score The score to be tested
     * @return isHiscore Returns true if the score given is large enough to be
     * on the hiscore board
     */
    public static boolean isHiscore(int givenScore)
    {
        return false;
    }
    
    public static void main(String [] args) throws FileNotFoundException
    {
        buildScoreArray();
    }
}
