/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Devin
 */
public class GameMain extends BasicGame{    
    Player player;
    
    TrueTypeFont ttFont;
    
    public GameMain(){
        super("Game Jam");
    }
    
    @Override
    public void init(GameContainer container) throws SlickException {
        java.awt.Font f = new java.awt.Font("Consolas", java.awt.Font.PLAIN, 15);
        ttFont = new TrueTypeFont(f, true);
        
        player = new Player(100, 100, .5f, "Intrivix/game/assets/cirlce.png");
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
        player.render(g);
    }
    
}