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
    private boolean isJumping = false, isDoubleJumping = false;
    private static final double JUMP_SPEED = 500;
    private double yAcceleration = 0;
    private double gravity = 9001;
    
    public Player(int x, int y, double scale, String imgName){
        super(x, y, scale, imgName);
        this.setActive(true);
        
        scale();
        setSpeed(0, .5);
    }
    
    public void updatePlayer(double fps){
        checkPos();
        super.updateSprite(fps);
        
        updateRotation(fps);
        this.rotate();
        
        updateGravity(fps);
    }
    
    private void updateRotation(double fps){
        this.rotation += rotationSpeed/fps * 10;
        //System.out.println("Rotation = "+rotation + ", rotSpeed = "+rotationSpeed);
        //System.out.println("rot += "+ (rotationSpeed/fps));
    }
    
    private void updateGravity(double fps){
        yspeed -= yAcceleration/fps;
        yAcceleration -= gravity/fps;
    }
    public void setPosStopFall(double locx, double locy){
        this.locx = locx;
        this.locy = locy;
        
        //locy = GamePanel.height/2;
        isJumping = false;
        isDoubleJumping = false;
        setSpeed(0, 0);
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
        if(isJumping){
            if(isDoubleJumping){
                return;
            }
            isDoubleJumping = true;
        }
        
        setSpeed(0, 1);
        isJumping = true;
        yAcceleration = JUMP_SPEED;
        yspeed = -yAcceleration;
        locy -= 10;
    }
    
}
