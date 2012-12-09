/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Container;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author Devin
 */

public class GameStarter extends JFrame {
    
    GameMenu menu;
    
    GameStarter(Boolean fullscreen){
        //Here is where we start the regular menu.
        //this class is simply here to start all the things
        
        super("$Game");
        
        if(fullscreen)
        {
            setBounds(50, 0, Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
        }
        else
        {
            setBounds(50, 0, GameMain.SMALL_SCREEN_WIDTH, GameMain.SMALL_SCREEN_HEIGHT);
        }
        
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        
        menu = new GameMenu(this);
        setContentPane(menu);
        setVisible(true);
    }
    
    public static void main(String[] args){
        GameStarter gs = new GameStarter(false);
    }
}