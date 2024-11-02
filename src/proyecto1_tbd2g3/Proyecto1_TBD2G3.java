/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1_tbd2g3;

/**
 *
 * @author dfcm9
 */
public class Proyecto1_TBD2G3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hola tigre");
        System.out.println("Nos conectaremos a Elasticache");
        
        ConexionDB con = new ConexionDB();
        con.Conectar();
        // TODO code application logic here
    }
    
}
