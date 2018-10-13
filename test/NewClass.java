
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Fede
 */
public class NewClass {
    public static void main(String[] args) {
        File archivo=new File("Productos.txt");
        ArrayList<String> kdena = new ArrayList<String>();
        ArrayList<String> Lacteos1 = new ArrayList<String>();
        ArrayList<String> Carnes1 = new ArrayList<String>();
        ArrayList<String> Verduras1 = new ArrayList<String>();
        
        try{
           String  cadena;
            
           FileReader f = new FileReader(archivo);
           BufferedReader b = new BufferedReader(f);
           while((cadena = b.readLine())!=null) {
               kdena.add(cadena);
               
            }
            b.close();  
        }catch(Exception e){}
        
        int marcadorLacteos = kdena.indexOf("LACTEOS");
        int marcadorCarnes = kdena.indexOf("CARNES");
        int marcadorVerduras= kdena.indexOf("VERDURAS");
       
        //añadir lacteos
        for( int i= marcadorLacteos+1;i<marcadorCarnes;i++){
            Lacteos1.add(kdena.get(i));
        }
        //añadir carnes
        for(int i=marcadorCarnes+1;i<marcadorVerduras;i++){
            Carnes1.add(kdena.get(i));
        }
        //añadir verduras
        for(int i=marcadorVerduras+1;i<kdena.size();i++){
            Verduras1.add(kdena.get(i));
        }
        
        
        
        
    }//fin main
    
}
