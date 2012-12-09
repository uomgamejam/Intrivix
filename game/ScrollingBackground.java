/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Graphics2D;

/**
 *
 * @author georgevanburgh
 */
public class ScrollingBackground {
    private String img;
    private Sprite background1, background2;
    
    public ScrollingBackground(String imgName){ img = imgName; }
    
    public void initBG(int width, int height, double screenScale){
        background1 = new Sprite(0, 0, screenScale, img);
        background1.scale();
        background2 = new Sprite(background1.width, 0, screenScale, img);
        background2.scale();
        
        background1.setActive(true);
        background2.setActive(true);
    }
    
    public void updateBG(double FPS){
        background1.updateSprite(FPS);
        background2.updateSprite(FPS);
    }
    public void drawBG(Graphics2D g){
        background1.drawSprite(g);
        background2.drawSprite(g);
    }
    
    public void setSpeed(int x, int y, double xspeed){
        background1.setSpeed(x, y);
        background2.setSpeed(x, y);
        
        background1.xspeed = xspeed;
        background2.xspeed = xspeed;
    }
    
}
