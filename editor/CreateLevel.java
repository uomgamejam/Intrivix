/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.editor;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author georgevanburgh
 */
public class CreateLevel {
    
    public static void makeLevel(String givenLevelName, String[] objects)
    {
        String LEVEL_DIR = "./src/Intrivix/game/assets/levels/";
        String levelFileName = LEVEL_DIR + givenLevelName + ".txt";
        try
        {    
        FileWriter fWrite = new FileWriter(levelFileName);
        BufferedWriter out = new BufferedWriter(fWrite);
        for (int index = 0; index < objects.length; index += 4)
        {
            String assetType = null;
            String assetFileName = null;
            switch(objects[(index + 3)])
            {
                case "ground":
                    assetType = "ground";
                    assetFileName = "DefaultBrick.png";
                    break;
                case "background":
                    assetType = "background";
                    assetFileName = "DefaultBackground.png";
                    break;
                default:
                    System.err.println("An invalid object type was requested");
                    break;
            }
            int xLoc = Integer.parseInt(objects[(index)]);
            int yLoc = Integer.parseInt(objects[(index + 1)]);
            double scaler = Double.parseDouble(objects[(index + 2)]);
            out.write(assetFileName + "\t" + xLoc + "\t" + yLoc + "\t" + scaler + "\t" + assetType + "\n");
        }
        out.close();
        }
        catch (Exception e)
        {
            System.err.println("An error occurred whilst writing to file" + e.getMessage());
        }
    }
    public static void main(String [] args)
    {
        String levelName = "level6";
        String[] testObjects = {"100", "100", "1", "ground", "300", "100", "1", "ground"}; 
        //testObjects = {"100", "100", "1", "ground", "300", "100", "1", "ground"};
        makeLevel(levelName, testObjects);
    }
}
