/*
 * create 8.12.12
 * class for the main menu 
 */
package game;

/**
 *
 * @author Richard de Mellow
 */


import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameMenu extends JFrame implements KeyListener
{
    
    //constructer class 
    public GameMenu()
    {
        setTitle("$Game");
        
        Container menuContent = getContentPane();
        menuContent.setLayout(new BorderLayout());
        
        
        
        menuContent.add(new JLabel("$Game"), BorderLayout.NORTH);
        
        JPanel centerButtons = new JPanel();
        
        
    }
    
    public void keyReleased(KeyEvent e) {}
    
    public void keyPressed(KeyEvent e){}
    
    public void keyTyped(KeyEvent e){}
    
}
