/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

/**
 *
 * @author George
 */
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelLoader {
    //String to contain the location of the level files
    private final static String LEVEL_DIR = "assets/levels/";
    private static ArrayList groundObjects = new ArrayList();
    private static ArrayList backgroundObjects = new ArrayList();
    private static ScrollingBackground scrollingBackground = null;
    private static PlayerStart playerStart = null;
    
    public static void LevelLoader(String levelname)
    {
        Scanner scan = null;
        try {
            String levelPath;
            levelPath = LEVEL_DIR + levelname;
            scan = new Scanner(new File(levelPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LevelLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (scan.hasNextLine())
        {
            String [] row = scan.nextLine().split("\\s+");
            String objectType = row[4];
            switch(objectType)
            {
                case "ground":
                    System.out.println("I shall create a ground object");
                    Ground groundObject = new Ground(Integer.parseInt(row[1]), Integer.parseInt(row[2]), Double.parseDouble(row[3]), row[0]);
                    groundObjects.add(groundObject);
                    break;
                case "background":
                    System.out.println("I shall create a background object");
                    Background backgroundObject = new Background(Integer.parseInt(row[1]), Integer.parseInt(row[2]), Double.parseDouble(row[3]), row[0]);
                    backgroundObjects.add(backgroundObject);
                    break;
                case "scrolling-background":
                    System.out.println("I shall create a scrolling background object");
                    scrollingBackground = new ScrollingBackground(Integer.parseInt(row[1]), Integer.parseInt(row[2]), Double.parseDouble(row[3]), row[0]);
                    break;
                case "player-start":
                    System.out.println("I shall create a player start object");
                    playerStart = new PlayerStart(Integer.parseInt(row[1]), Integer.parseInt(row[2]), Double.parseDouble(row[3]), row[0]);
                    break;
                default:
                    System.err.println("Level file: Undefined object");
            }
        }
    }
    public static void main(String [] args)
    {
        LevelLoader("level1.txt");
    }
}

