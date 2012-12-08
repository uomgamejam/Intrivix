/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.io.IOException;
import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Devin LR
 */
public class Sprite {
    
    // image-related
    private String imageName;
    private Texture image;
    
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
        try{
            System.out.println(imgName + " ");
            image = TextureLoader.getTexture("png", ResourceLoader.getResourceAsStream(imageName));
        }catch (IOException ioe){
            System.out.println("IO Exception: " + ioe);
        }
        if (image == null) {    // no image of that name was found
          System.err.println("No sprite image for " + imageName);
          System.exit(1);
        }
        else {
          width = image.getImageWidth();
          height = image.getImageHeight();
        }
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
