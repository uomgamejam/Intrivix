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
    Player player;
    
    GamePanel panel;
    
    public GameMain(){
        super("Game Jam");
        panel = new GamePanel(this);
        setBounds(50,0,640,480);
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        setContentPane(panel);
        setVisible(true);
    }
    
}