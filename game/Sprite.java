/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devin LR
 */
public class Sprite {
    
    // image-related
    private String imageName;
    private Image image;
    
    private float scale;
    
    public int width, height;
    
    protected double locy, locx;// location of sprite
    protected double dx, dy;//speed for this sprite...
    
    public Sprite(int x, int y, float scale, String imgName)
    {
        locx = x;
        locy = y;
        
        this.scale = scale;
        
        dx = 0;
        dy = 0;


        imageName = imgName;
        
    } // end of Sprite()
    
    public void setSpeed(double x, double y){
        dy = y;
        dx = x;
    }
    
    public Point getLocation(){
        return new Point((int)locx, (int)locy);
    }

    public Rectangle getMyRect(){
        return new Rectangle((int)locx, (int)locy, width, height);
    }
    
    public void setPosition(int x, int y){
        locx = x;
        locy = y;
    }
    
}
