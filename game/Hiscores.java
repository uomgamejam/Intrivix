/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.io.*;
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
    public static boolean isHiscore(String givenName, String givenScore, String givenDate) throws FileNotFoundException
    {
        Boolean isHiscore = false;
        String[][] scoreArray = buildScoreArray();
        for (int index = 0; index < 10; index++)
        {
            if (Integer.parseInt(givenScore) > Integer.parseInt(scoreArray[index][1])) {
                isHiscore = true;
                scoreArray[index][0] = givenName;
                scoreArray[index][1] = givenScore;
                scoreArray[index][2] = givenDate;
                writeArray(scoreArray);
                break;
            }
        }
        return isHiscore;
    }
    
    public static void writeArray(String[][] scoreArray)
    {
        try
        {    
        FileWriter fWrite = new FileWriter(HISCORE_PATH);
        BufferedWriter out = new BufferedWriter(fWrite);
        for (int index = 0; index < 10; index++)
        {
                int indexTwo = 0;
                String name = scoreArray[index][indexTwo];
                String date = scoreArray[index][indexTwo + 2];
                String score = scoreArray[index][indexTwo + 1];
                out.write(name + "\t" + score + "\t" + date + "\n");
        }
        out.close();
        }
        catch(Exception e)
        {
            System.err.println("An error occured while writing the hiscore");
        }
    }
    
    public static void main(String [] args) throws FileNotFoundException
    {
        buildScoreArray();
        if(isHiscore("Richard", "5001", "12/12/2012"))
        {
            System.out.println("Well done - Hiscore!!");
        }
    }
}
