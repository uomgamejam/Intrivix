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
    
    private double rotationSpeed = 0;
    
    //code needed for the player's jump
    private boolean isJumping = false;
    private static final double JUMP_SPEED = 50;
    private double yAcceleration = JUMP_SPEED;
    private double gravity = 10;
    
    public Player(int x, int y, double scale, String imgName){
        super(x, y, scale, imgName);
        this.setActive(true);
        
        scale();
    }
    
    public void updatePlayer(double fps){
        checkPos();
        super.updateSprite(fps);
        
        updateRotation(fps);
        this.rotate();
        
        if(isJumping){
            updateJump(fps);
        }
    }
    
    private void updateRotation(double fps){
        this.rotation += rotationSpeed/fps * 10;
        //System.out.println("Rotation = "+rotation + ", rotSpeed = "+rotationSpeed);
        //System.out.println("rot += "+ (rotationSpeed/fps));
    }
    
    private void updateJump(double fps){
        yspeed += yAcceleration;
        yAcceleration -= gravity/fps;
    }
    
    /**
     * This sets the speed of the player ball's rotation in
     * degrees per second
     * @param rotSpeed the rotation speed in degrees per second
     */
    public void setRotationSpeed(double rotSpeed){
        this.rotationSpeed = rotSpeed;
    }

    private void checkPos() {
        //TODO here we make sure the player is still on
        //the ground of some sort... we should add some physics
        //for all of the jumps and such...
    }
    
    public void jump(){
        isJumping = true;
        setSpeed(0, JUMP_SPEED);
    }
    
}
