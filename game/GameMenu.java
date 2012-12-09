       /*
 * create 8.12.12
 * class for the main menu 
 */
package Intrivix.game;

/**
 *
 * @author Richard de Mellow
 */



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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
    private boolean fullscreen = false;
    
    private BufferedImage background;
    
    private double keyDownTime;
    private int currentButtonIndex = 1;
    //constructer class 
    public GameMenu(GameStarter gs)
    {
        gameStarter = gs;
        
        width = gameStarter.getBounds().width;
        height = gameStarter.getBounds().height;
        
        screenScale = width/(double) GameMain.TARGET_SCREEN_WIDTH;
  
        background = ImageLoader.INSTANCE.loadImage("GUI/background.png");
        menuButtonsB[0] = ImageLoader.INSTANCE.loadImage("GUI/title.png");
        menuButtonsB[1] = ImageLoader.INSTANCE.loadImage("GUI/newgame.png");
        menuButtonsB[2] = ImageLoader.INSTANCE.loadImage("GUI/hiscores.png");
        menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/fullscreen.png");
        menuButtonsB[4] = ImageLoader.INSTANCE.loadImage("GUI/exit.png");
       setFocusable(true);
       addKeyListener(this); 
       
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
        drawBackground(xg, background);
        menuRender();
    }
    private void menuRender(){
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
                g.drawImage(image, x, y, image.getWidth()/2 - 1, image.getHeight()/2,null);
            }
        }
    }
    
    private void drawBackground(Graphics g, BufferedImage image)
    {
        g.drawImage(image,0,0,width,height,null);
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
              menuUpdate();
              render();
              paintScreen();
              if (!gameOver) {
                  afterTime = System.nanoTime()/1000000;
                  timeScale = afterTime - beforeTime;
                  if(timeScale > 0){
                    FPS = (int) (1000 / timeScale);
                  }else{
                      FPS = 0;
                  }
                  beforeTime = System.nanoTime()/1000000;
              }
          }
	}
        //System.exit(0);
    }
    
    private void menuUpdate(){
        switch(currentButtonIndex)
        {
            case 1:menuButtonsB[1] = ImageLoader.INSTANCE.loadImage("GUI/newgame_selected.png");
                   menuButtonsB[2] = ImageLoader.INSTANCE.loadImage("GUI/hiscores.png");
                   menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/fullscreen.png");
                   menuButtonsB[4] = ImageLoader.INSTANCE.loadImage("GUI/exit.png");
                break;
            case 2:menuButtonsB[2] = ImageLoader.INSTANCE.loadImage("GUI/hiscores_selected.png");
                   menuButtonsB[1] = ImageLoader.INSTANCE.loadImage("GUI/newgame.png");
                   menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/fullscreen.png");
                   menuButtonsB[4] = ImageLoader.INSTANCE.loadImage("GUI/exit.png");
                break;
            case 3: 
                   if(fullscreen){
                    menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/windowed_selected.png");
                   }
                   else
                   {
                    menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/fullscreen_selected.png");
                   }
                   menuButtonsB[2] = ImageLoader.INSTANCE.loadImage("GUI/hiscores.png");
                   menuButtonsB[1] = ImageLoader.INSTANCE.loadImage("GUI/newgame.png");
                   menuButtonsB[4] = ImageLoader.INSTANCE.loadImage("GUI/exit.png");
                break;
            case 4:menuButtonsB[4] = ImageLoader.INSTANCE.loadImage("GUI/exit_selected.png");
                   menuButtonsB[1] = ImageLoader.INSTANCE.loadImage("GUI/newgame.png");
                   menuButtonsB[2] = ImageLoader.INSTANCE.loadImage("GUI/hiscores.png");
                   menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/fullscreen.png"); 
                break;
        }
    }
    
    private void callMethodForButton()
    {
        switch(currentButtonIndex)
        {
            case 1: GameMain game = new GameMain();
                    gameStarter.setVisible(false); //you can't see me!
                    running = false;
                    gameStarter.dispose();
                    game.setVisible(true);
                break;
            case 2: 
                break;
            case 3: fullscreen = true;
                    gameStarter.setVisible(false); //you can't see me!
                    running = false;
                    gameStarter.dispose();
                    GameStarter gs = new GameStarter(true);
                    menuButtonsB[3] = ImageLoader.INSTANCE.loadImage("GUI/windowed.png"); 
                    System.out.println("going full screen");
                break;
            case 4: System.exit(0);
                break;         
        }
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
    
    @Override
    public void keyPressed(KeyEvent e){
        System.out.println("some shit was pressed ");
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE && keyDownTime == 0){
            System.out.println("Space button pressed");
            keyDownTime = System.currentTimeMillis();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        double keyElapceTime = System.currentTimeMillis() - keyDownTime;
        if(keyCode == KeyEvent.VK_SPACE){
            if(keyElapceTime > 200){
                System.out.println("trying to open app");
                callMethodForButton();
                keyDownTime = 0;
            }
            else
            {
                keyDownTime = 0;
                if(currentButtonIndex == 4){
                    currentButtonIndex = 1;
                }
                else
                {
                    currentButtonIndex += 1;
                }
                System.out.println(currentButtonIndex);
            }
        }
        keyDownTime = 0;
    }
    
    @Override
    public void keyTyped(KeyEvent e){}
    
}
