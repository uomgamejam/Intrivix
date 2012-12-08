/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Intrivix.game;

/**
 *
 * @author Devin
 */
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;     // for ImageIcon


public class ImageLoader
{
  private final static String IMAGE_DIR = "assets/";
  private HashMap imagesMap;
  private HashMap gNamesMap;

  private GraphicsConfiguration gc;
  ArrayList imsList = new ArrayList();


  public ImageLoader(String fnm)
  // begin by loading the images specified in fnm
  { initLoader();
    loadSingleImage(fnm);
  }  // end of ImagesLoader()

  public ImageLoader()
  {  initLoader();  }


  private void initLoader()
  {
      imagesMap = new HashMap();
      gNamesMap = new HashMap();

      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
  }  // end of initLoader()

  // --------- load a single image -------------------------------

  public boolean loadSingleImage(String fnm)
  // can be called directly
  {
    BufferedImage bi = loadImage(fnm);
    if (bi != null) {
      ArrayList imsList = new ArrayList();
      imsList.add(bi);
      imagesMap.put(fnm, imsList);
      System.out.println("  Stored " + fnm + "/" + fnm);
      return true;
    }
    else
      return false;
  }  // end of loadSingleImage()

  // ------------------ access methods -------------------

  public BufferedImage getImage(String name)
  /* Get the image associated with <name>. If there are
     several images stored under that name, return the
     first one in the list.
  */
  {
    ArrayList imsList = (ArrayList) imagesMap.get(name);
    if (imsList == null) {
      System.out.println("No image(s) stored under " + name);
      //return null;
    }

    // System.out.println("Returning image stored under " + name);
    //return (BufferedImage) imsList.get(0);
    System.out.println("loadImage");
    return loadImage(name);
  }  // end of getImage() with name input;

  public boolean isLoaded(String name)
  // is <name> a key in the imagesMap hashMap?
  {
    ArrayList imsList = (ArrayList) imagesMap.get(name);
    if (imsList == null)
      return false;
    return true;
  }  // end of isLoaded()

   public BufferedImage loadImage(String fnm)
   /* Load the image from <fnm>, returning it as a BufferedImage
      which is compatible with the graphics device being used.
      Uses ImageIO.
   */
   {
     try {
       BufferedImage im =  ImageIO.read(
                      getClass().getResource(IMAGE_DIR + fnm) );
       // An image returned from ImageIO in J2SE <= 1.4.2 is
       // _not_ a managed image, but is after copying!

       int transparency = im.getColorModel().getTransparency();
       BufferedImage copy =  gc.createCompatibleImage(
                                im.getWidth(), im.getHeight(),
		                        transparency );
       // create a graphics context
       Graphics2D g2d = copy.createGraphics();
       // g2d.setComposite(AlphaComposite.Src);

       // reportTransparency(IMAGE_DIR + fnm, transparency);

       // copy image
       g2d.drawImage(im,0,0,null);
       g2d.dispose();
       return copy;
     }
     catch(IOException e) {
       System.out.println("Load Image error for " +
                     IMAGE_DIR + "/" + fnm + ":\n" + e);
       return null;
     }
  } // end of loadImage() using ImageIO


  private BufferedImage makeBIM(Image im, int width, int height)
  // make a BufferedImage copy of im, assuming an alpha channel
  {
    BufferedImage copy = new BufferedImage(width, height,
                                        BufferedImage.TYPE_INT_ARGB);
    // create a graphics context
     Graphics2D g2d = copy.createGraphics();
    // g2d.setComposite(AlphaComposite.Src);

    // copy image
    g2d.drawImage(im,0,0,null);
    g2d.dispose();
    return copy;
  }  // end of makeBIM()



  public BufferedImage loadImage3(String fnm)
  /* Load the image from <fnm>, returning it as a BufferedImage.
     Use Image.
  */
  { Image im = readImage(fnm);
    if (im == null)
      return null;

    int width = im.getWidth( null );
    int height = im.getHeight( null );

    return makeBIM(im, width, height);
  } // end of loadImage() using Image


  private Image readImage(String fnm)
  // load the image, waiting for it to be fully downloaded
  {
    Image image = Toolkit.getDefaultToolkit().getImage(
                     getClass().getResource(IMAGE_DIR + fnm) );
    MediaTracker imageTracker = new MediaTracker( new JPanel() );

    imageTracker.addImage(image, 0);
    try {
      imageTracker.waitForID(0);
    }
    catch (InterruptedException e) {
      return null;
    }
    if (imageTracker.isErrorID(0))
      return null;
    return image;
  } // end of readImage()
}
