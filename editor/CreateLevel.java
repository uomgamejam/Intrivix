/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.editor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author georgevanburgh
 */
public class CreateLevel {
    
    public static void makeLevel(String givenLevelName, String[] objects) throws IOException
    {
        String levelFileName = givenLevelName + ".txt";
        FileWriter fWrite = new FileWriter(levelFileName);
        BufferedWriter out = new BufferedWriter(fWrite);
    }
}
