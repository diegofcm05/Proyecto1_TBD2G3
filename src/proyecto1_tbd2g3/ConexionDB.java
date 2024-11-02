/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_tbd2g3;
import java.io.IOException;
import net.spy.memcached.MemcachedClient;
import java.net.InetSocketAddress;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.DefaultConnectionFactory;

/**
 *
 * @author dfcm9
 */
public class ConexionDB {
    String endpoint = "proyecto1tbd2g3.shbfc6.0001.use1.cache.amazonaws.com";
    int port = 11211;
    private MemcachedClient client;

    public void conectar() {
        try {
            client = new MemcachedClient(AddrUtil.getAddresses(endpoint + ":" + port));

            System.out.println("Conectado a Memcached en AWS ElastiCache");
        } catch (IOException e) {
            System.err.println("Error al conectar a Memcached: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void almacenarDato(String clave, String valor) {
        if (client != null) {
            client.set(clave, 3600, valor); // Almacena el valor durante 3600 segundos (1 hora)
            System.out.println("Dato almacenado: " + clave + " -> " + valor);
        } else {
            System.err.println("El cliente no está conectado. No se puede almacenar el dato.");
        }
    }

    public String recuperarDato(String clave) {
        if (client != null) {
            String valor = (String) client.get(clave);
            System.out.println("Dato recuperado: " + clave + " -> " + valor);
            return valor;
        } else {
            System.err.println("El cliente no está conectado. No se puede recuperar el dato.");
            return null;
        }
    }

    public void cerrar() {
        if (client != null) {
            client.shutdown();
            System.out.println("Cliente de Memcached cerrado.");
        }
    }
}