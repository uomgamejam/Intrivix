/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Container;
import javax.swing.JFrame;

/**
 *
 * @author Devin
 */

public class GameStarter extends JFrame {
    
    GameMenu menu;
    
    GameStarter(){
        //Here is where we start the regular menu.
        //this class is simply here to start all the things
        
        super("$Game");
        setBounds(50, 0, GameMain.SMALL_SCREEN_WIDTH, GameMain.SMALL_SCREEN_HEIGHT);
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        
        menu = new GameMenu(this);
        setContentPane(menu);
        setVisible(true);
    }
    
    public static void main(String[] args){
        GameStarter gs = new GameStarter();
    }
}