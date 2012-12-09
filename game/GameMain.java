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
public class GameMain extends JFrame{
    public static final int SMALL_SCREEN_WIDTH = 800;
    public static final int SMALL_SCREEN_HEIGHT = 450;
    
    public static final int TARGET_SCREEN_WIDTH = 1920;
    public static final int TARGET_SCREEN_HEIGHT = 1080;
    
    Player player;
    
    GamePanel panel;
    private GraphicsDevice gd;
    
    public GameMain(boolean fullScreen){
        /*super("$Game");
        setBounds(50, 0, SMALL_SCREEN_WIDTH, SMALL_SCREEN_HEIGHT);
        setResizable(false);
        ExitWindow exit = new ExitWindow();
        addWindowListener(exit);
        
        panel = new GamePanel(this);
        setContentPane(panel);
        setVisible(true);*/
        super("$Game");
        
        if(fullScreen)
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
        
        panel = new GamePanel(this, fullScreen);
        setContentPane(panel);
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
    }
    
    public static void main(String[] args){
        GameMain game = new GameMain(true);
    }
    
}