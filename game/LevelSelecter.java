/*
 * class to allow users to select a level
 */
package Intrivix.game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

/**
 *
 * @author rich
 */


public class LevelSelecter extends JFrame{
    
    private GraphicsDevice gd;
    private LevelSelecterLogic newPanel;
    
    public LevelSelecter(boolean fullscreen)
    {
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
        newPanel = new LevelSelecterLogic(this, fullscreen);
        setContentPane(newPanel);
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
}
