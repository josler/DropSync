/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package v1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jamie
 */
public class GeditThread extends Thread {

    String str = null;
    Merger mf = null;

    GeditThread(String sfilea, Merger mf) {
        str = sfilea;
        this.mf = mf;
    }
    
    @Override
    public void run() {
        try {
            Process p = Runtime.getRuntime().exec("gedit " + str);
            //p.waitFor();
         
            while(p.exitValue() != 0) {
                /*try {
                    int x = p.exitValue();
                    break;
                } catch (Exception e) {
                    
                } */
             }
        } catch (Exception e) {
            
        }
        synchronized(mf) {
          mf.notify();
        }
    }
    
}
