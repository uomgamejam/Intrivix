/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;
/**
 *
 * @author Devin
 */
import game.GameMenu;
public class GameStarter {
    
    GameStarter(){
        //Here is where we start the regular menu.
        //this class is simply here to start all the things
        GameMenu gm = new GameMenu();
    }
    
    public static void main(String[] args){
        GameStarter gs = new GameStarter();
    }
}