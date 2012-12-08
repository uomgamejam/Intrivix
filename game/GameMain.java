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
    
    Player player;
    
    GamePanel panel;
    
    public GameMain(){
        super("Game Jam");
        panel = new GamePanel(this);
        setBounds(50,0,SMALL_SCREEN_WIDTH,SMALL_SCREEN_HEIGHT);
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        setContentPane(panel);
        setVisible(true);
    }
    
    public static void main(String[] args){
        GameMain game = new GameMain();
    }
    
}