/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import javax.swing.JFrame;

/**
 *
 * @author Devin
 */
public class GameMain extends JFrame{
    public static final int SMALL_SCREEN_WIDTH = 800;
    public static final int SMALL_SCREEN_HEIGHT = 450;
    
    public static final int TARGET_SCREEN_WIDTH = 1960;
    public static final int TARGET_SCREEN_HEIGHT = 1080;
    
    Player player;
    
    GamePanel panel;
    
    public GameMain(){
        super("Game Jam");
        setBounds(50, 0, SMALL_SCREEN_WIDTH, SMALL_SCREEN_HEIGHT);
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        
        panel = new GamePanel(this);
        setContentPane(panel);
        setVisible(true);
    }
    
    public static void main(String[] args){
        GameMain game = new GameMain();
    }
    
}