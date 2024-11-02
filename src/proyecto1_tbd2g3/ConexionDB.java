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
    String endpoint = "proyecto1tbd2g3-shbfc6.serverless.use1.cache.amazonaws.com";
    int port = 11211;
    
    public void Conectar() {
        MemcachedClient client = null;
        try {
            

            // Configura el cliente con un timeout más alto
            client = new MemcachedClient(
                new DefaultConnectionFactory() {
                    @Override
                    public long getOperationTimeout() {
                        return 5000; // Timeout de 5 segundos
                    }
                },
                AddrUtil.getAddresses(endpoint + ":" + port)
            );

            System.out.println("Conectado a Memcached en AWS ElastiCache");

            // Ejemplo: almacenar un valor en el caché
            client.set("claveEjemplo", 3600, "Hola desde Java con Memcached!");

            // Recuperar el valor del caché
            String valor = (String) client.get("claveEjemplo");
            System.out.println("Valor recuperado: " + valor);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
    }

    
}
