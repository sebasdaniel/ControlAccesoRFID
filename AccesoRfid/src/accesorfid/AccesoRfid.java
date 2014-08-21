///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package accesorfid;
//
////import java.sql.ResultSet;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//
////import java.sql.ResultSetMetaData;
////import java.sql.SQLException;
////import java.sql.Statement;
////import java.util.logging.Level;
////import java.util.logging.Logger;
//
///**
// *
// * @author 53B45
// */
//public class AccesoRfid {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        BaseDatos bd = new BaseDatos();
//        
//        bd.conectardBD();
//        
////        try {
////            Statement instruc = bd.getInstruccion();
////            
////            ResultSet resultado = instruc.executeQuery("SELECT * FROM usuario");
////            
////            ResultSetMetaData metaDatos = resultado.getMetaData();
////            
////            //mostrar cabezera de la tabla
////            for(int i = 1; i <= metaDatos.getColumnCount(); i++){
////                System.out.printf("%-8s\t", metaDatos.getColumnName(i));
////            }
////            
////            System.out.println();
////            
////            //mostrar los datos de la tabla
////            while(resultado.next()){
////                
////                for(int i = 1; i<= metaDatos.getColumnCount(); i++){
////                    System.out.printf("%-8s\t", resultado.getObject(i));
////                }
////                System.out.println();
////            }
////            
////        } catch (SQLException ex) {
////            Logger.getLogger(AccesoRfid.class.getName()).log(Level.SEVERE, null, ex);
////        }
//        
//        String dias[] = {"domingo", "lunes", "martes", "miercoles", "jueves", "viernes", "sabado"};
//        
//        int bloque = 3;
//        
//        ArrayList<Integer> listaAulas = bd.getAulas(bloque);
//        
//        System.out.println("Lista de Aulas");
//        
//        for(int i=0; i<listaAulas.size(); i++){
//            System.out.println(i + ": " + listaAulas.get(i));
//        }
//        
//        System.out.println();
//        
//        int id = 1;
//        
//        Calendar calendario = Calendar.getInstance();
//        
//        int dia = calendario.get(Calendar.DAY_OF_WEEK);
//        
//        // si la hora es en formato de 12 horas usar Calendar.HOUR
//        String hora = calendario.get(Calendar.HOUR_OF_DAY) + ":" + calendario.get(Calendar.MINUTE) + ":"
//                + calendario.get(Calendar.SECOND);
//        
//        System.out.println("dia: " + dias[dia-1] + "\nhora: " + hora + "\n");
//        
//        System.out.println(bd.consultar(id, dias[1], hora));
//        
//        System.out.println(bd.consultar(1, dias[1], "3:00:00"));
//        
//        bd.desconectarDB();
//    }
//    
//}
