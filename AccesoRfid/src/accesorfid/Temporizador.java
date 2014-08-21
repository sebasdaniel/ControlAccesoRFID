/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesorfid;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 53B45
 */
public class Temporizador implements Runnable {

    private final int tiempo;
    private final OutputStream output;
    private final String pin;
    
    public  Temporizador(int temporizacion, OutputStream outStream, int info){
        
        tiempo = temporizacion * 60000;
        output = outStream;
        pin = info+"\n";
        
    }
    
    @Override
    public void run() {
        
        if(output != null){
            
            try {

                Thread.sleep(tiempo);
//                System.out.println(pin);
                output.write(pin.getBytes());
                output.flush();

            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(Temporizador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
