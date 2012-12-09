/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

/**
 *
 * @author georgevanburgh
 */
public class Hiscore {
    private static String name, date;
    private static int score;
    public Hiscore(String givenName, int givenScore, String givenDate)
    {
        name = givenName;
        score = givenScore;
        date = givenDate;
    }
    public static String returnName()
    {
        return name;
    }
    public static String returnDate()
    {
        return date;
    }
    public static int returnScore()
    {
        return score;
    }
}
