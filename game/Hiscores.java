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
    public static String[][] buildScoreArray() throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(HISCORE_PATH));
        String[][] scoreArray = new String[10][3];
        int row = 0;
        while(input.hasNextLine())
        { 
           String column[] = input.nextLine().split("\\s+");
           for (int index = 0; index < 3; index++ ) 
           {
                scoreArray[row][index] = column[index];
           }
           row++;
        }
        for(int colIndex = 0; colIndex < 10; colIndex++)
        {
            for(int rowIndex = 0; rowIndex < 3; rowIndex++)
            {
                System.out.println(scoreArray[colIndex][rowIndex]);
            }
        }
        
        return scoreArray;
    }
    
    /**
     * @param score The score to be tested
     * @return isHiscore Returns true if the score given is large enough to be
     * on the hiscore board
     */
    public static boolean isHiscore(int givenScore)
    {
        Boolean isHiscore = false;
        String[][] scoreArray = buildScoreArray();
        for (int index = 0; index < 10; index++)
        {
            isHiscore = (givenScore < scoreArray[index][1]);
        }
        return false;
    }
    
    public static void main(String [] args) throws FileNotFoundException
    {
        buildScoreArray();
    }
}
