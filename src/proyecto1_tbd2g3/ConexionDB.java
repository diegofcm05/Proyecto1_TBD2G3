/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1_tbd2g3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
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

    private static AmazonDynamoDB client;
    private static DynamoDB dynamoDB;
    private static String tableName = "Persona";

    // Establecer las credenciales de AWS
    private String accessKey = "ASIA6ODU5YVH7YBRYVJQ"; // Reemplaza con tu Access Key ID
    private String secretKey = "qNH32h+OoRjn++mqiDrP0faB7d9Sg+IUMULiPFQT"; // Reemplaza con tu Secret Access Key
    private String sessionToken = "IQoJb3JpZ2luX2VjEG8aCXVzLXdlc3QtMiJGMEQCIGFPbPzxJJCzTcHbZ6NIOMBkqsfteHr1MckoCQwtAAb9AiAska3MSYr+0tITdjW9g1B0djKqXdbA2W4gRdavShM44Sq9Agjo//////////8BEAAaDDk5MjM4MjY2NjA2MyIM42uJAF7Fi6EczaalKpECwvwZobZD3OLy1ODeLWlabjhiUq+52nqGZik6GsvyaNSZ8VJaDUA0EBmcX/coHEzoT0mnYMLUUAPADa5Lfxumzt3EV19oAVXiz3YyPHrqb2ohGsTwouNdkHA6DtDqCMHeLUqRfx2up9Io4+S00nHaRgi6GMMGd2jeOZDaNYonJNnHDytCOzVtvsFhjeHP8wI2rwcitZqM0etFwleMFWy/jf5jC2IpQS9dPsZhYfCFL0dzbMNdLn6aVM+MIlE8VF02l5nrcdoAzKKxbf03v1qhWpdsBYA1MOBVYXh6tYigZVIFUYwofwajWcm3YGDD3WsDJnKdrV64O3wsZgfaMdcQqawjigYEIkRee9fXbObiD0RBMJzhobkGOp4BY+36TJDE+Fqp3Gy0Ok+gqFCTO+puTOrUyP2DVmrXqcnl+60JWj6I4DkRcPy5tWLO3yz4CNNCBstDJP4tz+8tcCn8DjhEGsd/sbXvXQ86p9IQLdcivMDaVlpkzLZBYMGpoWSKs8EZ6OCeVLD86dKViHnFmXJfWaySA1D/xlYgYFT3lk0vENCYVdeFen1sRPym9hrWoAPcu+LYWpXawpE=";

    public void Conectar() {
        // Crear las credenciales con el token de sesión
        BasicSessionCredentials awsCreds = new BasicSessionCredentials(accessKey, secretKey, sessionToken);

        // Inicializar el cliente de DynamoDB
        client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-east-1") // Cambia a tu región
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        dynamoDB = new DynamoDB(client);
        System.out.println("Se ha establecido una conexion con DynamoDB!");
    }

    // Método para insertar una persona
    public void insertPerson(String id, String nombre, String apellido, int edad) {
        try {
            // Obtener la tabla
            Table table = dynamoDB.getTable(tableName);

            // Crear un nuevo ítem
            Item item = new Item()
                    .withPrimaryKey("IdPersona", id)
                    .withString("nombre", nombre)
                    .withString("apellido", apellido)
                    .withNumber("edad", edad);

            // Insertar el ítem en la tabla
            table.putItem(item);
            System.out.println("Persona insertada correctamente: " + item.toJSON());

        } catch (Exception e) {
            System.out.println("No se pudo insertar la persona: " + e.getMessage());
        }
    }

    public void insertFarmacia(String id, String nombre, String direccion, String propietario_id, String nombre_propietario) {
        try {
            // Obtener la tabla
            Table table = dynamoDB.getTable(tableName);

            // Crear un nuevo ítem
            Item item = new Item()
                    .withPrimaryKey("id_farmacia", id)
                    .withString("Nombre", nombre)
                    .withString("Direccion", direccion)
                    .withString("ID_Propietario", propietario_id);

            // Insertar el ítem en la tabla
            table.putItem(item);
            System.out.println("Farmacia insertada correctamente: " + item.toJSON());

        } catch (Exception e) {
            System.out.println("No se pudo insertar la persona: " + e.getMessage());
        }
    }

    public void insertLaboratorio(String id_lab, String dir, String contact) {
        try {
            Table table = dynamoDB.getTable(tableName);
            Item item = new Item().withPrimaryKey("id_laboratorio", id_lab).withString("Direccion", dir).withString("Contacto", contact);
            table.putItem(item);
            System.out.println("Laboratorio insertado correctamente");
        } catch (Exception e) {
            System.out.println("Error al insertar el laboratorio");
        }
    }

    public void insertProduct(String id, String nombre,String fabricante, String family, String owner, int precio_Coste, int precio_Venta, int unidades, boolean subvencionado) {
        try {
            // Obtener la tabla
            Table table = dynamoDB.getTable(tableName);

            // Crear un nuevo ítem
            Item item = new Item()
                    .withPrimaryKey("id_producto", id)
                    .withString("Nombre", nombre)
				.withString("Fabricante", fabricante)
				.withString("Familia", family)
				.withNumber("Precio_Coste", precio_Coste)
				.withNumber("Precio_Venta", precio_Venta)
				.withNumber("Unidades", unidades)
				.withBoolean("Subvencionado", subvencionado);

            // Insertar el ítem en la tabla
            table.putItem(item);
            System.out.println("Producto insertado correctamente: " + item.toJSON());

        } catch (Exception e) {
            System.out.println("No se pudo insertar el producto: " + e.getMessage());
        }
    }
    
    public void insertFarmaceutico(String id_farm, String nom){
	try {
		Table table = dynamoDB.getTable(tableName);
		Item item = new Item().withPrimaryKey("id_farmaceutico", id_farm).withString("Nombre", nom);
		table.putItem(item);
		System.out.print("Farmaceutico insertado exitosamente");
	} catch (Exception e) {
		System.out.print("Error al insertar el farmaceutico");
	}
}
    
    public void insertCliente(String id_cliente, String nom){
	try {
		Table table = dynamoDB.getTable(tableName);
		Item item = new Item().withPrimaryKey("id_cliente", id_cliente).withString("Nombre", nom);
		table.putItem(item);
		System.out.print("Cliente insertado exitosamente");
	} catch (Exception e) {
		System.out.print("Error al insertar el cliente");
	}
}
    
    public void insertPropietario(String id_owner, String nom){
	try {
		Table table = dynamoDB.getTable(tableName);
		Item item = new Item().withPrimaryKey("id_propietario", id_owner).withString("Nombre", nom);
		table.putItem(item);
		System.out.print("Propietario insertado exitosamente");
	} catch (Exception e) {
		System.out.print("Error al insertar el propietario");
	}
    }
    
    public void insertPedido(String id_pedido, String farmaciaID, int total){
	try {
		Table table = dynamoDB.getTable(tableName);
		Item item = new Item().withPrimaryKey("id_pedido", id_pedido)
                        .withString("Farmacia", farmaciaID)
                        .withNumber("Total", total);
		table.putItem(item);
		System.out.print("Pedido insertado exitosamente");
	} catch (Exception e) {
		System.out.print("Error al insertar el pedido");
	}
    }
    
    
    public void insertDetallePedido(String id_pedido, String ProductoID, int cantidad , int total){
	try {
		Table table = dynamoDB.getTable(tableName);
		Item item = new Item().withPrimaryKey("id_pedido", id_pedido)
                        .withString("Producto", ProductoID)
                        .withNumber("Cantidad", cantidad)
                        .withNumber("Total", total);
		table.putItem(item);
		System.out.print("Detalle de Pedido insertado exitosamente");
	} catch (Exception e) {
		System.out.print("Error al insertar el detalle de Pedido");
	}
    }
    
    // Método para recuperar una persona
    public void retrievePerson(String id) {
        try {
            // Obtener la tabla
            Table table = dynamoDB.getTable(tableName);

            // Recuperar el ítem por la clave primaria
            Item item = table.getItem("IdPersona", id); // Cambia "IdPersona" si tu clave primaria tiene otro nombre

            if (item != null) {
                // Mostrar los atributos del ítem
                System.out.println("Persona recuperada: " + item.toJSON());
            } else {
                System.out.println("No se encontró la persona con ID: " + id);
            }

        } catch (Exception e) {
            System.out.println("Error al recuperar la persona: " + e.getMessage());
        }
    }

}
