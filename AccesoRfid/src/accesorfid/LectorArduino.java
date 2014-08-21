/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesorfid;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JTextArea;

/**
 *
 * @author 53B45
 */
public class LectorArduino implements Runnable {

    private InputStream input;
    private String salida;
    private JTextArea miArea;
    private boolean continuar;
    private BaseDatos conex;
    private ArrayList<Integer> lista;
    private Calendar calendario;
    
    public LectorArduino(InputStream inStream, JTextArea area, BaseDatos con, ArrayList<Integer> listAulas){
        input = inStream;
        salida = "";
        miArea = area;
        continuar = true;
        conex = con;
        lista = listAulas;
    }
    
    @Override
    public void run() {
        
        if(input != null){
            
            byte[] buffer = new byte[1024];
            int len;
            String temp = "";

            try{
                while (continuar) {

                    len = input.read(buffer);                    
                    temp += new String(buffer, 0, len);
                    
                    if(temp.contains("\n")){
                        
                        
                        try{
//                            System.out.println(temp);
                            int numPin = (Integer.parseInt(temp.replaceAll("[\n\r]", "")) - 9);
                            
//                            System.out.println(numPin);
                            
                            int id = lista.get(numPin - 2);
//                            System.out.println(id);
                            calendario = Calendar.getInstance();

                            int dia = calendario.get(Calendar.DAY_OF_WEEK);

                            String hora = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) + ":"
                                    + calendario.get(Calendar.SECOND);
//                            System.out.println(dia + " - " + hora);
                            conex.conectardBD();
                            int result = conex.consultarAbiertas(id, App.dias[dia-1], hora);
                            conex.desconectarDB();
//                            System.out.println(result);
                            
                            if(result != -1){
                                salida += "[" + hora + "] El aula " + result + " esta abierta\n";
                                miArea.setText(salida);
                            }
                            
                        }catch(NumberFormatException ex){
                            System.err.println("Error al convertir!");
                        }catch(IndexOutOfBoundsException ex){
                            System.err.println("Error, aula no asignada!");
                        }
                        
                        temp = "";
                    }

                }
            }catch(IOException ex){
                System.err.println(ex);
            }
            
        }
    }
    
//    private int numeroAula(int index){
//        
//        return lista.get(index);
//    }
    
    public void detener(){
        continuar = false;
    }
    
}
