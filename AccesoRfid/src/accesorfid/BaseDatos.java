/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accesorfid;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 53B45
 */
public class BaseDatos {
    
    private Connection conexion;
    private Statement instruccion;
    private final String CONTROLADOR = "com.mysql.jdbc.Driver";
    private final String RUTA_DB = "jdbc:mysql://localhost:3306/control_acceso";
    
//    private int bloque;
    
    public BaseDatos(){
        
        conexion = null;
        instruccion = null;
        
//        bloque = bloq;
        
    }
    
    /**
     * @return the instruccion
     */
    public Statement getInstruccion() {
        return instruccion;
    }
    
    public void conectardBD(){
        
        try {
            Class.forName(CONTROLADOR);
            
            conexion = DriverManager.getConnection(RUTA_DB, "rfid", "acceso");
            
            instruccion = conexion.createStatement();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desconectarDB(){
        try {
            instruccion.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int consultar(long id, String dia, String hora){
        
        try {
            
            String sentencia = "select aula.id from profesor,dicta_clase,aula where profesor.id=" + id
                    + " and dia='" + dia + "' and h_inicio<='" + hora + "' and h_final>='" + hora
                    + "' and profesor.id=dicta_clase.profesor and aula.id=dicta_clase.aula";

            ResultSet resultado = instruccion.executeQuery(sentencia);
            
            // si arrojo un resultado
            if(resultado.next()){
                return (int) resultado.getObject(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
        
    }
    
    public int consultarAbiertas(int aulaId, String dia, String hora){
        
        try {
            
            String sentencia = "select aula.numero from dicta_clase,aula where aula.id=" + aulaId
                    + " and not (dia='" + dia + "' and h_inicio<='" + hora + "' and h_final>='" + hora
                    + "') and dicta_clase.aula=aula.id";

            ResultSet resultado = instruccion.executeQuery(sentencia);
            
            // si arrojo un resultado
            if(resultado.next()){
                return (int) resultado.getObject(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
        
    }
    
    public ArrayList<Integer> getAulas(int bloque){
        
        ArrayList<Integer> aulas = new ArrayList();
        
        try {
            
            String sentencia = "select aula.id from aula,bloque where bloque.numero=" + bloque
                    + " and aula.bloque=bloque.numero order by aula.numero";

            ResultSet resultado = instruccion.executeQuery(sentencia);
            
            while(resultado.next()){
                aulas.add((Integer) resultado.getObject(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return aulas;
        
    }
    
}
