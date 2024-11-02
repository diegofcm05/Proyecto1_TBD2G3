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
        ConexionDB conexion = new ConexionDB();
        conexion.conectar();
        
        //Almacenar un dato
        //conexion.almacenarDato("mi_clave3", "mi_valor3");
        
        // Recuperar un dato
        conexion.recuperarDato("mi_clave3");

        
        // Cerrar la conexión
        conexion.cerrar();
    }
    
}
