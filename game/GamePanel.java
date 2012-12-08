/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author Devin LR
 */
public class GamePanel extends JPanel implements Runnable, MouseListener, KeyListener{    
    
    GameMain gameFrame;
    
    private Thread thread;
    
    private long gameStartTime;
    private boolean running;
    private boolean gameOver;
    private long timeScale;
    private int FPS;
    private boolean isPaused;
    
    private boolean fullscreen = false;
    
    Player player;
    
    GamePanel(GameMain frame){
        gameFrame = frame;
        
        setFocusable(true);
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
        
    }
    
    private void paintScreen(){
        
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
                  FPS = (int) (1000 / timeScale);
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
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing here...
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
