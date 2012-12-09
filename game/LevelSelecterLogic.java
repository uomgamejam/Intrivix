/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author rich
 */
public class LevelSelecterLogic extends JPanel implements Runnable, KeyListener {

    private Thread threadLoadMenu;
    private long gameStartTime;
    private boolean running;
    private boolean gameOver;
    private long timeScale;
    private int FPS;
    private boolean isPaused;
    private Image xImage = null;
    private Graphics2D xg = null;
    private int width;
    private int height;
    private double screenScale;
    private boolean fullscreen;
    LevelSelecter levelSelecter;
    private int currentLevelLoadIndex = 1;
    private Font font = new Font("Serif", Font.BOLD, 30);
    private BufferedImage background;
    private double keyDownTime;

    public LevelSelecterLogic(LevelSelecter ls, boolean full) {
        levelSelecter = ls;
        fullscreen = full;

        width = levelSelecter.getBounds().width;
        height = levelSelecter.getBounds().height;

        background = ImageLoader.INSTANCE.loadImage("GUI/background.png");
        setFocusable(true);
        addKeyListener(this);

        try {
            InputStream fin = this.getClass().getResourceAsStream("fonts/ikarus.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, fin).deriveFont(30f);
            font = font.deriveFont(Font.BOLD);
        } catch (FontFormatException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("font error");
        } catch (IOException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("font error");
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();
        startLoadMenu();
    }

    private void startLoadMenu() {
        if (threadLoadMenu == null) {
            threadLoadMenu = new Thread(this);
            threadLoadMenu.start();
        }
    }

    private void drawBackground(Graphics g, BufferedImage image) {
        g.drawImage(image, 0, 0, width, height, null);
    }

    private void render() {
        if (xImage == null) {
            xImage = createImage(width, height);
            if (xImage == null) {
                System.out.println("xImage is null");
                return;
            } else {
                xg = (Graphics2D) xImage.getGraphics();
            }
        }

        xg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawBackground(xg, background);
        loadMenuRender();
    }

    private void resetLoadMenu() {
        drawLevelText(xg, 100, "Level One", false);
        drawLevelText(xg, 150, "Level Two", false);
        drawLevelText(xg, 200, "Level Three", false);
        drawLevelText(xg, 250, "Level Four", false);
        drawLevelText(xg, 300, "Level Five", false);
    }

    private void loadMenuRender() {
        resetLoadMenu();
        switch (currentLevelLoadIndex) {

            case 1:
                drawLevelText(xg, 100, "Level One", true);
                break;
            case 2:
                drawLevelText(xg, 150, "Level Two", true);
                break;
            case 3:
                drawLevelText(xg, 200, "Level Three", true);
                break;
            case 4:
                drawLevelText(xg, 250, "Level Four", true);
                break;
            case 5:
                drawLevelText(xg, 300, "Level Five", true);
                break;
        }

    }

    private void drawLevelText(Graphics2D g, int height, String text, boolean selected) {
        if (selected) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN);
        }
        g.setFont(font);
        g.drawString(text, width / 2 - 100, height);

    }

    private void paintScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (xImage != null)) {
                g.drawImage(xImage, 0, 0, null);
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }

    public void run() {
        long beforeTime, afterTime;
        gameStartTime = System.nanoTime();
        beforeTime = gameStartTime / 1000000;

        running = true;

        while (running) {
            if (!gameOver) {
                //menuUpdate();
                render();
                paintScreen();
                if (!gameOver) {
                    afterTime = System.nanoTime() / 1000000;
                    timeScale = afterTime - beforeTime;
                    if (timeScale > 0) {
                        FPS = (int) (1000 / timeScale);
                    } else {
                        FPS = 0;
                    }
                    beforeTime = System.nanoTime() / 1000000;
                }
            }
        }
        //System.exit(0);
    }

    private void startGame(int levelNum) {
        levelSelecter.setVisible(false); //you can't see me!
        running = false;
        levelSelecter.dispose();

        //if (menu.getFullScreen()) {
            
            GameMain game = new GameMain(false, levelNum);
            game.setVisible(true);
        /*} else {
            fullscreen = true;
            GameStarter gs = new GameStarter(true);
        }*/
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE && keyDownTime == 0) {
            System.out.println("Space button pressed");
            keyDownTime = System.currentTimeMillis();
        }
    }

    public void keyReleased(KeyEvent e) {
        double timeElapse = System.currentTimeMillis() - keyDownTime;
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (timeElapse > 200) {
                startGame(currentLevelLoadIndex);
            } else {
                keyDownTime = 0;
                if (currentLevelLoadIndex == 5) {
                    currentLevelLoadIndex = 1;
                } else {
                    currentLevelLoadIndex += 1;
                }
            }

        }
        System.out.println("current index :" + currentLevelLoadIndex);
    }

    public void keyTyped(KeyEvent e) {
    }
}
