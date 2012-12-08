/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package Intrivix.game;

/**
 *
 * @author George
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelLoader {
    //String to contain the location of the level files
    private final static String LEVEL_DIR = "assets/levels/";
    
    public LevelLoader(String levelname)
    {
        String levelPath = LEVEL_DIR + levelname;
        Scanner input = null;
        try {
            input = new Scanner(new File(levelPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(input.hasNextLine())
        {
            String line = input.nextLine();
            System.out.println(line);
        }
    }
    public static void main(String [] args)
    {
        LevelLoader("level1.txt");
    }
}

