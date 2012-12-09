/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Intrivix.game;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Devin LR
 */
public class ExitWindow extends WindowAdapter {
    ExitWindow(){

    }
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
