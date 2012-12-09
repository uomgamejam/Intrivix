/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author georgevanburgh
 */
public class RandomLevel {
    private static int MIN_SHIFT = -2;
    private static int MAX_SHIFT = 2;
    private static String randomLevelFileName = "./src/Intrivix/game/assets/levels/random";
    public static int randomNumber(int Min, int Max)
    {
        int random = Min + (int)(Math.random() * ((Max - Min) + 1));
        return random;
    }
    
    public static String addBlocks(int numberOfBlocks) throws IOException
    {
        int initialX = 0;
        String toWrite = null;
        for (int index = 0; index < numberOfBlocks; index++)
         {
             int randomShift = (50 * randomNumber(MIN_SHIFT, MAX_SHIFT));
             toWrite += ("DefaultBrick.png \t" + initialX + "\t" + (100 + randomShift) + "\t 1 \t ground \n");
             initialX += 100;
             
         }
        return toWrite;
    }
    
    public static void writeString(String toWrite) throws IOException
    {
        FileWriter fWrite = new FileWriter(randomLevelFileName);
        BufferedWriter out = new BufferedWriter(fWrite);
        out.write(toWrite);
        out.close();
    }
    
    public static void main(String [] args) throws IOException
    {
        String straightBlocks = "DefaultBrick.png 	0	100	 1 	 ground \n" +
                                "DefaultBrick.png 	100	100	 1 	 ground \n" +
                                "DefaultBrick.png 	200	100	 1 	 ground \n" +
                                "DefaultBrick.png 	300	100	 1 	 ground ";
        String blockData = addBlocks(100);
        writeString(straightBlocks + blockData + "null \t 100 \t 150 \t 1 \t playerstart \n background.png \t . \t . \t scrolling-background \n");
    }
}
