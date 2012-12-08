/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Intrivix.game;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Devin LR
 */
public class DelayAction {
    
    private double delayTime = 0;
    private double elapsedTime;
    
    Object target;
    String method;
    
    Method methodToBeCalled;
    
    public DelayAction(double delayTime, Object o, String method){
        target = o;
        this.method = method;
        
        try {
            Class<?> cls = target.getClass();
            methodToBeCalled = cls.getMethod(method);
        } catch (NoSuchMethodException e) {
        }
    }
    
    public void update(double FPS){
        elapsedTime += 1/FPS;
        
        if(elapsedTime > delayTime){
            try {
                methodToBeCalled.invoke(target);
            } catch (Exception ex) {
                Logger.getLogger(DelayAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
