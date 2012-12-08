/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

/**
 *
 * @author Devin LR
 */
public class Player extends Sprite{
    
    // default step sizes (how far to move in each update)
    private static final double XMOV = 5;
    private static final double YMOV = 5;
    
    public Player(int x, int y, int w, int h, String imgName){
        super(x, y, w, h, imgName);
    }
    
}
