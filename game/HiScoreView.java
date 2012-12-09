/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author rich
 */
public class HiScoreView extends JFrame{
     private final JTextArea resultArea = new JTextArea(20, 10);
     private Scanner scan;
     
     public HiScoreView()
     {
         setTitle("$Game Results!!!!!!!!!!!!");
         Container highScores = getContentPane();
         highScores.setLayout(new BorderLayout());
         
         highScores.add(resultArea);
         highScores.add(new JScrollPane(resultArea), BorderLayout.CENTER);
        
         getResults();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
     }
     
     public void getResults()
     {
         try {
            scan = new Scanner(new File("./src/Intrivix/game/assets/hiscore"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HiScoreView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (scan.hasNextLine()){
            String temp = scan.nextLine();
            resultArea.append("\n" + temp);
        }
     }
}
