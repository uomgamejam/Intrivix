/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

/**
 *
 * @author georgevanburgh
 */
public class Ground extends Sprite {
    
    private String textureName;
    private int xLoc, yLoc;
    private double scale;
    
    public Ground(int x, int y, double givenScale, String imgName)
    {
        super(x, y, givenScale, imgName);
    }
    @Override
    public String toString()
    {
        return ("Texture: " + textureName + ", xLoc: " + xLoc 
                + ", yLoc: " + yLoc + "Scale: " + scale);
    }
}
