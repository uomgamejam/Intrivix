/*
 * create 8.12.12
 * class for the main menu 
 */
package Intrivix.game;

/**
 *
 * @author Richard de Mellow
 */



import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameMenu extends JPanel implements Runnable, KeyListener
{
    GameStarter gameStarter;
    
    private Thread thread;
    private long gameStartTime;
    private boolean running;
    private boolean gameOver;
    private long timeScale;
    private int FPS;
    private boolean isPaused;
    
    private BufferedImage[] menuButtonsB = new BufferedImage[5];
    
    private Image xImage = null;
    private Graphics2D xg = null;
    
    private int width;
    private int height;
    private double screenScale;
    
    private Sprite background;
    
    private double keyDownTime;
    private int currentButtonIndex = 0;
    //constructer class 
    public GameMenu(GameStarter gs)
    {
        gameStarter = gs;
        
        width = gameStarter.getBounds().width;
        height = gameStarter.getBounds().height;
        
        screenScale = (double) (width / 1920);
  
        background = new Sprite(0, 0, screenScale, "GUI/background.png");
        menuButtonsB[0] = ImageLoader.INSTANCE.loadImage("GUI/title.png");
        menuButtonsB[1] = ImageLoader.INSTANCE.loadImage("GUI/newgame.png");
        menuButtonsB[2] = ImageLoader.INSTANCE.loadImage("GUI/hiscores.png");
        menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/fullscreen.png");
        menuButtonsB[4] = ImageLoader.INSTANCE.loadImage("GUI/exit.png");
       
        
    }
    
    private void render(){
        if (xImage == null){
          xImage = createImage(width, height);
          if (xImage == null) {
            System.out.println("xImage is null");
            return;
          }
          else
          {
            xg = (Graphics2D) xImage.getGraphics();
          }
        }

        xg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        xg.setColor(new Color(0, 0, 0));
        xg.fillRect (0, 0, width, height);

        menuRender();
    }
    private void menuRender(){
        xg.setColor(Color.black);
        drawImageButton(xg, menuButtonsB[0], (width/2 - 150), 0,true);
        drawImageButton(xg, menuButtonsB[1], (width/2 - 50), 90, false);
        drawImageButton(xg, menuButtonsB[2], (width/2 - 50), 120, false);
        drawImageButton(xg, menuButtonsB[3], 30, (height - 50), false);
        drawImageButton(xg, menuButtonsB[4], (width - 130), (height - 50), false);
        //TODO draw all the things
    }
    
    private void drawImageButton(Graphics g, BufferedImage image, int x, int y, Boolean title) {
        if (image == null) {   // the sprite has no image
            System.out.println("no image for button ");
            return;
        } else {
            if(title)
            {
               g.drawImage(image, x, y, image.getWidth()/3, image.getHeight()/3,null); 
            }
            else
            {
                g.drawImage(image, x, y, image.getWidth()/2, image.getHeight()/2,null);
            }
        }
    }
    
    private void paintScreen(){
        Graphics g;
        try {
          g = this.getGraphics();
          if ((g != null) && (xImage != null)) {
            g.drawImage(xImage, 0, 0, null);
          }
          g.dispose();
        }
        catch (Exception e){
            System.out.println("Graphics context error: " + e);
        }
    }
    
    @Override
    public void run() {
        long beforeTime, afterTime;
        gameStartTime = System.nanoTime();
        beforeTime = gameStartTime/1000000;

	running = true;

	while(running) {
          if (!gameOver) {
              //gameUpdate();
              render();
              paintScreen();
              if (!gameOver) {
                  afterTime = System.nanoTime()/1000000;
                  timeScale = afterTime - beforeTime;
                  if(timeScale > 0)
                    FPS = (int) (1000 / timeScale);
                  else
                      FPS = 0;
                  beforeTime = System.nanoTime()/1000000;
              }
          }
	}
        System.exit(0);
    }
    
    private void gameUpdate(){
        //TODO possibly do something here...
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        startMenu();
    }
    
    private void startMenu(){
        if (thread == null) {
          thread = new Thread(this);
              thread.start();
        }
    }
    
    public void keyPressed(KeyEvent e){
        
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            System.out.println("Space button pressed");
            keyDownTime = System.currentTimeMillis();
        }
    }
    
    public void keyReleased(KeyEvent e) {
        int keyCode =e.getKeyCode();
        double keyElapceTime = System.currentTimeMillis() - keyDownTime;
        if(keyCode == KeyEvent.VK_SPACE){
            if(keyElapceTime <= 500){
                if(currentButtonIndex == 4){
                    currentButtonIndex = 0;
                }
                else
                {
                    currentButtonIndex += 1;
                }
                System.out.println(currentButtonIndex);
            }
        }
    }
    
    public void keyTyped(KeyEvent e){}
    
}
