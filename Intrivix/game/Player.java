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
    
    public Player(int x, int y, float scale, String imgName){
        super(x, y, scale, imgName);
        this.setActive(true);
    }
    
    public void updatePlayer(float fps){
        checkPos();
        super.updateSprite(fps);
    }

    private void checkPos() {
        //TODO here we make sure the player is still on
        //the ground of some sort... we should add some physics
        //for all of the jumps and such...
    }
    
}
