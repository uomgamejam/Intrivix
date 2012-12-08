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
    private float scale;
    
    public Ground(String textureName, int xLoc, int yLoc, float scale)
    {
        textureName = textureName;
        xLoc = xLoc;
        yLoc = yLoc;
        scale = scale;
    }
    @Override
    public String toString()
    {
        return ("Texture: " + textureName + ", xLoc: " + xLoc 
                + ", yLoc: " + yLoc + "Scale: " + scale);
    }
}
