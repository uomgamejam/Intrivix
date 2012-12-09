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
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Devin LR
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    GameMain gameFrame;
    private Thread thread;
    private long gameStartTime;
    private boolean running = false;
    private boolean gameOver = true;
    private long timeScale;
    private double FPS = 1;
    private boolean isHighScore = false;
    private char letter1, letter2, letter3;
    private int currentLetterChanged = 1;
    private boolean fullscreen = false;
    private BufferedImage bgImage;
    private Graphics2D xg = null;
    private Image xImage = null;
    private Font font = new Font("Serif", Font.BOLD, 30);
    public static int width, height;
    private double screenScale;
    private long startTime, elapsedTime;
    private boolean inCountDown = true;
    private double countDownRemain = 0;
    private long keyDownTime = 0;
    private double screenMovAccel = -3;
    private double screenMovSpeed = -750;
    private double totalDistance = 0;
    private int levelNum;
    Player player;

    GamePanel(GameMain frame, boolean fullScreen, int levelNum) {
        gameFrame = frame;
        this.fullscreen = fullScreen;
        this.levelNum = levelNum;
        setFocusable(true);
        width = gameFrame.getBounds().width;
        height = gameFrame.getBounds().height;

        screenScale = width / (double) GameMain.TARGET_SCREEN_WIDTH;
        screenMovSpeed *= screenScale;

        addKeyListener(this);

        player = new Player((int) (width * 2 / 5), (int) (height / 2), .3 * screenScale, "circle.png");


        init();

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

        gameOver = false;
    }

    private void init() {
        player.setPosition((int) (width * 2 / 5), (int) (height / 2));
        player.setRotationSpeed(screenMovSpeed / -3);


        String levelName = "level" + levelNum;
        if (levelNum == 5) {
            try {
                RandomLevel.generateLevel();
                LevelLoader.loadLevel(levelName);
            } catch (IOException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (levelNum != 0) {
            LevelLoader.loadLevel(levelName);
        } else {
            LevelLoader.loadLevel("level1");
        }

        System.out.println("we've loaded all the things");

        //LevelLoader.playerStart;
        for (int i = 0; i < LevelLoader.groundObjects.size(); i++) {
            double xDiff = LevelLoader.playerStart.xloc
                    - LevelLoader.groundObjects.get(i).locx;
            double yDiff = LevelLoader.playerStart.yloc
                    - LevelLoader.groundObjects.get(i).locy;

            LevelLoader.groundObjects.get(i).locx = player.locx - xDiff;
            LevelLoader.groundObjects.get(i).locy = height - (player.locy - yDiff);

            LevelLoader.groundObjects.get(i).setSpeed(1, 0);
            LevelLoader.groundObjects.get(i).xspeed = screenMovSpeed;
        }

        for (Sprite s : LevelLoader.backgroundObjects) {
            s.setSpeed(1, 0);
            s.xspeed = screenMovSpeed;
        }

        LevelLoader.scrollingBackground.initBG(width, height, screenScale);
    }

    private void startGame() {
        if (thread == null || !running) {
            thread = new Thread(this);
            thread.start();
        }
    }

    private void gameUpdate() {
        if (!gameOver) {
            if (inCountDown) {
                updateCountDown();

            } else {
                player.updatePlayer(FPS);

                for (Sprite s : LevelLoader.groundObjects) {
                    s.updateSprite(FPS);
                    if (player.getMyRect().intersects(s.getMyRect())) {
                        if ((player.locy + player.height / 2) < s.locy) {
                            player.setPosStopFall(player.locx, s.locy - player.height);
                            //System.out.println("player loc = "+player.locx+","+player.locy);
                            //System.out.println("block loc = "+s.locx+","+s.locy);
                        } else {
                            //This means we have now lost the game... Shit... I just lost the game -.-
                            //TODO lose the game (check)
                            System.out.println("the game should have ended by now >.>");
                            lostGame();
                        }

                    }
                }
                for (Sprite s : LevelLoader.backgroundObjects) {
                    s.updateSprite(FPS);
                }

                if (player.locy > height) {
                    lostGame();
                }

                LevelLoader.scrollingBackground.updateBG(FPS);

                updateScreenMov();
            }
        }
    }

    private void updateCountDown() {
        elapsedTime = System.currentTimeMillis() - startTime;
        countDownRemain = 5 - elapsedTime / 1000.0;

        if (countDownRemain <= 0) {
            this.inCountDown = false;
        }
    }

    private void updateScreenMov() {
        screenMovSpeed += screenMovAccel / FPS;
        totalDistance += screenMovSpeed / FPS;

        player.setRotationSpeed(screenMovSpeed / -3);

        for (Sprite s : LevelLoader.backgroundObjects) {
            s.setSpeed(1, 0);
            s.xspeed = screenMovSpeed;
        }
        for (Sprite s : LevelLoader.groundObjects) {
            s.setSpeed(1, 0);
            s.xspeed = screenMovSpeed;
        }

        LevelLoader.scrollingBackground.setSpeed(1, 0, screenMovSpeed / 3);
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
        if (bgImage != null) {
            xg.drawImage(bgImage, 0, 0, null);
        } else {
            xg.setColor(new Color(0, 0, 0));
            xg.fillRect(0, 0, width, height);
        }

        if (running) {
            gameRender();
        }
        if (gameOver) {
            gameOverMessage(xg);
        }
    }

    private void gameRender() {
        xg.setColor(Color.black);

        LevelLoader.scrollingBackground.drawBG(xg);

        for (int i = 0; i < LevelLoader.backgroundObjects.size(); i++) {
            LevelLoader.backgroundObjects.get(i).drawSprite(xg);
        }

        player.drawSprite(xg);

        for (int i = 0; i < LevelLoader.groundObjects.size(); i++) {
            LevelLoader.groundObjects.get(i).drawSprite(xg);
        }

        drawHUD(xg);
        if (this.inCountDown) {
            drawCountDown(xg);
        }
    }

    private void drawCountDown(Graphics2D g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.setFont(font);

        g.drawString("" + Math.round(countDownRemain), (int) (width / 2), (int) (height / 2));

        g.setColor(c);
    }

    private void drawHUD(Graphics2D g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.setFont(font);

        g.drawString("Score: " + ((int) Math.abs(totalDistance)), (int) (100 * screenScale),
                (int) (100 * screenScale));

        g.setColor(c);
    }

    private void paintScreen() {
        Graphics g;
        try {
            g = this.getGraphics();
            if ((g != null) && (xImage != null)) {
                g.drawImage(xImage, 0, 0, null);
                //checkStats();
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }

    @Override
    public void run() {
        long beforeTime, afterTime;
        gameStartTime = System.nanoTime();
        beforeTime = gameStartTime / 1000000;

        startTime = System.currentTimeMillis();
        running = true;

        while (running) {
            gameUpdate();
            render();
            paintScreen();

            afterTime = System.nanoTime();
            timeScale = afterTime - beforeTime;
            if (timeScale > 0) {
                //System.out.println("Time Scale = "+timeScale);
                FPS = 1000000000 / timeScale;
                //System.out.println("FPS = "+FPS);
                if (FPS <= 10) {
                    FPS = 10;
                }
            } else {
                FPS = 100;
            }
            beforeTime = System.nanoTime();
        }
        //System.exit(0);
    }

    private void restartGame() {
        player = new Player((int) (width * 2 / 5), (int) (height / 2), .3 * screenScale, "circle.png");

        LevelLoader.backgroundObjects.clear();
        LevelLoader.groundObjects.clear();

        totalDistance = 0;

        init();

        LevelLoader.scrollingBackground.reinitBG(width, height);
        startTime = System.currentTimeMillis();
        inCountDown = true;
        gameOver = false;
    }

    private void lostGame() {
        this.gameOver = true;
        try {
            isHighScore = Hiscores.isHiscore("" + ((int) Math.round(totalDistance)));
            currentLetterChanged = 1;
            letter1 = 'a';
            letter2 = 'a';
            letter3 = 'a';
            System.out.println("isHighScore = " + isHighScore +", score="+((int) Math.abs(totalDistance)));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gameOverMessage(Graphics g) {
        Color c = g.getColor();
        if (keyDownTime != 0) {
            long keyPressTime = System.currentTimeMillis() - keyDownTime;
            int grn = keyPressTime < 255 ? (int) keyPressTime : 255;
            g.setColor(new Color(255 - grn, grn, 0));
        } else {
            g.setColor(new Color(255, 0, 0));
        }

        if (isHighScore) {
            g.drawString("GAME OVER", width / 2 - 150, height / 2 - 50);
            g.drawString("New High Score! Please enter your name:", width / 2 - 200, height / 2);
            g.drawString(letter1 + letter2 + letter3 + "", width / 2 - 225, height / 2 + 30);
        } else {
            g.drawString("GAME OVER", width / 2 - 150, height / 2 - 50);
            g.drawString("Press to restart!", width / 2 - 200, height / 2);
            g.drawString("Hold to go to the menu.", width / 2 - 225, height / 2 + 30);
        }

        g.setColor(c);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        startGame();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing here...
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE && keyDownTime == 0) {
            keyDownTime = System.currentTimeMillis();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            long keyPressTime = System.currentTimeMillis() - keyDownTime;
            if (keyPressTime > 200 && gameOver) {
                if (isHighScore) {
                    System.out.println("letter selection stuffs");
                    currentLetterChanged++;
                    if (currentLetterChanged > 3) {
                        try {
                            Hiscores.addHiscore(letter1 + letter2 + letter3 + "", "" + ((int) Math.round(totalDistance)),
                                    "");
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    keyDownTime = 0;
                } else {
                    System.out.println("This should go back to the menu now >.>");
                    returnToMenu();
                    keyDownTime = 0;
                }
            } else if (gameOver) {
                if (isHighScore) {
                    //restart the game!!! =P
                    System.out.println("change the current letter");
                    switch (currentLetterChanged) {
                        case 1:
                            incrLetter(letter1);
                            break;
                        case 2:
                            incrLetter(letter2);
                            break;
                        case 3:
                            incrLetter(letter3);
                            break;
                    }
                    restartGame();
                    keyDownTime = 0;
                } else {
                    //restart the game!!! =P
                    System.out.println("this should restart the level");
                    restartGame();
                    keyDownTime = 0;
                }
            } else {
                jumpBall();
                keyDownTime = 0;
            }
        }
    }

    private void incrLetter(char letter) {
        switch (letter) {
            case 'a':
                letter = 'b';
                break;
            case 'b':
                letter = 'c';
                break;
            case 'c':
                letter = 'd';
                break;
            case 'd':
                letter = 'e';
                break;
            case 'e':
                letter = 'f';
                break;
            case 'f':
                letter = 'g';
                break;
            case 'g':
                letter = 'h';
                break;
            case 'h':
                letter = 'i';
                break;
            case 'i':
                letter = 'j';
                break;
            case 'j':
                letter = 'k';
                break;
            case 'k':
                letter = 'l';
                break;
            case 'l':
                letter = 'm';
                break;
            case 'm':
                letter = 'n';
                break;
            case 'n':
                letter = 'o';
                break;
            case 'o':
                letter = 'p';
                break;
            case 'p':
                letter = 'q';
                break;
            case 'q':
                letter = 'r';
                break;
            case 'r':
                letter = 's';
                break;
            case 's':
                letter = 't';
                break;
            case 't':
                letter = 'u';
                break;
            case 'u':
                letter = 'v';
                break;
            case 'v':
                letter = 'w';
                break;
            case 'w':
                letter = 'x';
                break;
            case 'x':
                letter = 'y';
                break;
            case 'y':
                letter = 'z';
                break;
            case 'z':
                letter = 'a';
                break;
        }
    }

    private void returnToMenu() {
        running = false;
        LevelLoader.backgroundObjects.clear();
        LevelLoader.groundObjects.clear();
        LevelLoader.playerStart = null;
        LevelLoader.scrollingBackground = null;

        GameStarter gs = new GameStarter(false);
        this.gameFrame.setVisible(false);
        running = false;
        gameFrame.dispose();
        gs.setVisible(true);
    }

    private void jumpBall() {
        //System.out.println("Player is jumping...");
        player.jump();

    }
}
