/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Devin LR
 */
public class GamePanel extends JPanel implements Runnable, KeyListener{    
    
    GameMain gameFrame;
    
    private Thread thread;
    
    private long gameStartTime;
    private boolean running;
    private boolean gameOver;
    private long timeScale;
    private int FPS;
    private boolean isPaused;
    
    private boolean fullscreen = false;
    private BufferedImage bgImage;
    private Graphics2D xg = null;
    private Image xImage = null;
    
    private int width, height;
    
    Player player;
    
    GamePanel(GameMain frame){
        gameFrame = frame;
        
        setFocusable(true);
        width = gameFrame.getBounds().width;
        height = gameFrame.getBounds().height;
        
        addKeyListener(this);
        
        player = new Player(100, 100, .5f, "circle.png");
    }
    
    private void startGame(){
        if (thread == null || !running) {
          thread = new Thread(this);
              thread.start();
        }
    }
    
    private void gameUpdate(){
        if (!isPaused && !gameOver) {
            player.updatePlayer(FPS);
        }
    }
    
    private void render(){
        if (xImage == null){
          xImage = createImage(width, height);
          if (xImage == null) {
            System.out.println("xImage is null");
            return;
          }
          else
            xg = (Graphics2D) xImage.getGraphics();
        }

        xg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if(bgImage != null){
            xg.drawImage(bgImage, 0, 0, null);
        }else{
            xg.setColor(new Color(0, 0, 0));
                xg.fillRect (0, 0, width, height);
        }

        if (running && !gameOver)  {
            gameRender();
        }
        //if (gameOver)
          //gameOverMessage(xg);
    }
    
    private void gameRender(){
        xg.setColor(Color.black);

        player.drawSprite(xg);
    }
    
    private void paintScreen(){
        Graphics g;
        try {
          g = this.getGraphics();
          if ((g != null) && (xImage != null)) {
            g.drawImage(xImage, 0, 0, null);
            //checkStats();
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
              gameUpdate();
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
    
    @Override
    public void addNotify(){
        super.addNotify();
        startGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing here...
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //nothing much...
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //nothing much for now...
    }
    
}