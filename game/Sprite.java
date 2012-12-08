/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
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
    private BufferedImage image, imageConst;
    
    private GraphicsConfiguration graphics;
    
    protected double scale;
    protected double rotation = 0, oldRot = 0;
    protected double xspeed, yspeed;
    
    public int width, height;
    
    private boolean isActive = false;
    
    protected double locy, locx;// location of sprite
    protected double dx, dy;//speed for this sprite...
    
    public Sprite(int x, int y, double scale, String imgName)
    {
        locx = x;
        locy = y;
        
        this.scale = scale;
        
        dx = 0;
        dy = 0;

        GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        graphics = environment.getDefaultScreenDevice().getDefaultConfiguration();

        imageName = imgName;
        image = ImageLoader.INSTANCE.loadImage(imageName);
        if (image == null) {    // no image of that name was found
            System.out.println("No sprite image for " + imageName);
        }
        else {
            width = image.getWidth();
            height = image.getHeight();
        }
        imageConst = image;
        
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
    
    public void updateSprite(double fps){
        if (fps !=0) {
            locx += dx/fps*xspeed;
            locy += dy/fps*yspeed;
        }
    }
    
    public void scale(){
        if (image == null) {
            System.out.println("input image is null");
            return;
        }
        
        int transparency = imageConst.getColorModel().getTransparency();
        BufferedImage dest =  graphics.createCompatibleImage((int)(imageConst.getWidth()*scale), 
                                                (int)(imageConst.getHeight()*scale), transparency );
        Graphics2D g2d = dest.createGraphics();

        AffineTransform origAT = g2d.getTransform(); // save original transform

        // rotate the coord. system of the dest. image around its center
        AffineTransform scale = new AffineTransform();
        scale.scale(this.scale, this.scale);
        g2d.transform(scale);

        g2d.drawImage(imageConst, 0, 0, null);   // copy in the image

        //g2d.setTransform(origAT);    // don't restore the original transform because we want to keep this...
        g2d.dispose();
        

        imageConst = dest;
        
        //make another image so that we have two copies of the image again...
        /*dest = graphics.createCompatibleImage(imageConst.getWidth(), imageConst.getHeight(), transparency );
        g2d = dest.createGraphics();
        g2d.drawImage(imageConst, 0, 0, null);
        image = dest;*/
    }
    
    public void rotate(){
        if (image == null) {
            System.out.println("input image is null");
            return;
        }
        
        int transparency = imageConst.getColorModel().getTransparency();
        BufferedImage dest =  graphics.createCompatibleImage(imageConst.getWidth(), imageConst.getHeight(), transparency );
        Graphics2D g2d = dest.createGraphics();

        AffineTransform origAT = g2d.getTransform(); // save original transform

        // rotate the coord. system of the dest. image around its center
        AffineTransform rot = new AffineTransform();
        rot.rotate( Math.toRadians(rotation), imageConst.getWidth()/2, imageConst.getHeight()/2);
        g2d.transform(rot);

        g2d.drawImage(imageConst, 0, 0, null);   // copy in the image

        g2d.setTransform(origAT);    // restore original transform
        g2d.dispose();

        image = dest;
        oldRot = rotation;
    }
    
    public void drawSprite(Graphics g){
        if (isActive()) {
            if (image == null) {   // the sprite has no image
                g.setColor(Color.yellow);   // draw a yellow circle instead
                g.fillOval(300, 300, 30, 30);
                g.setColor(Color.black);
            }
            else {
                g.drawImage(image, (int)locx, (int)locy, null);
            }
        }
    }
    
    
    public boolean isActive(){
        return isActive;
    }

    public void setActive(boolean a){
        isActive = a;
    }
    
}
