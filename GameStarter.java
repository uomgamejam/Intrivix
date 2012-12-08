/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Devin
 */
public class GameStarter {
    
    private GameMain gm;
    
    void run(){
        try {
            gm = new GameMain();
            AppGameContainer app = new AppGameContainer(gm);
            String[] ress = GameStarter.getResolutions();
            int[] temp = GameStarter.getResFromString(ress[ress.length-1]);
            app.setDisplayMode(temp[0], temp[1], false);
            app.start();
        } catch (SlickException ex) {
            Logger.getLogger(GameStarter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String[] getResolutions(){
        try{
            DisplayMode[] resolutions = Display.getAvailableDisplayModes();
            String[] possibleResolutions = new String[resolutions.length];
            for(int i=0; i<resolutions.length; i++){
                possibleResolutions[i] = resolutions[i].toString();
            }
            Comparator<String> sortByFirstNumber = new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int tempInt1 = Integer.parseInt(o1.split(" ")[0]);
                    int tempInt2 = Integer.parseInt(o2.split(" ")[0]);
                    
                    if(tempInt1 > tempInt2)
                        return -1;
                    else if(tempInt1 < tempInt2)
                        return 1;
                    else
                        return 0;
                }
            };
            Arrays.sort(possibleResolutions, sortByFirstNumber);
            return possibleResolutions;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static int[] getResFromString(String res){
        String[] resParts = res.split(" ");
        
        int[] results = new int[2];
        results[0] = Integer.parseInt(resParts[0]);
        results[1] = Integer.parseInt(resParts[2]);
        
        return results;
    }
    
    public static void main(String[] args){
        GameStarter gs = new GameStarter();
        gs.run();
    }
}