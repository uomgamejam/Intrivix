/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;


import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

/**
 *
 * @author Devin
 */

public class GameStarter extends JFrame {
    
    GameMenu menu;
    private GraphicsDevice gd;
    
    
    GameStarter(Boolean fullscreen){
        //Here is where we start the regular menu.
        //this class is simply here to start all the things
        
        super("$Game");
        
        if(fullscreen)
        {
            initFullScreen();
        }
        else
        {
            setBounds(50, 0, GameMain.SMALL_SCREEN_WIDTH, GameMain.SMALL_SCREEN_HEIGHT);
        }
        
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        
        menu = new GameMenu(this, fullscreen, 1);
        setContentPane(menu);
        setVisible(true);
    }
    
    private void initFullScreen() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gd = ge.getDefaultScreenDevice();

        setUndecorated(true);    // no menu bar, borders, etc. or Swing components
        setIgnoreRepaint(true);  // turn off all paint events since doing active rendering
        setResizable(false);

        if (!gd.isFullScreenSupported()) {
            System.out.println("Full-screen exclusive mode not supported");
            System.exit(0);
        }
        gd.setFullScreenWindow(this); // switch on full-screen exclusive mode
    }  // end of initFullScreen()
    
    
    public static void main(String[] args){
        GameStarter gs = new GameStarter(false);
    }
}