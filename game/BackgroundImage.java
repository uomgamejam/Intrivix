/*
 * class to create a background image for a JPane
 */
package game;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author rich
 */
public class BackgroundImage extends JPanel
{
    
    private Image backgroudImage;
    
    public BackgroundImage(String currentImg)
    {
        this(new ImageIcon(currentImg).getImage());
    }
    
    public BackgroundImage(Image backgroundImg) 
    {
        this.backgroudImage = backgroundImg;
        Dimension size = new Dimension(backgroundImg.getWidth(null),
                                       backgroundImg.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }
}
