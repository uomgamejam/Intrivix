/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

/**
 *
 * @author georgevanburgh
 */
public class ScrollingBackground {
    Sprite background;
    
    public ScrollingBackground(int x, int y, double givenScale, String imgName)
    {
        background = new Sprite(x, y, givenScale, imgName);
    }
}
