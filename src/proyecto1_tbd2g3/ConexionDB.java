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
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import java.io.IOException;
import net.spy.memcached.MemcachedClient;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
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
    private static String tableNameFarmacia = "Farmacia";
    private static String tableNameFarmaceutico = "Farmaceutico";
    private static String tableNamePropietario = "Propietario";

    // Establecer las credenciales de AWS
    private String accessKey = "ASIA6ODU5YVHVVVKUQS6"; // Reemplaza con tu Access Key ID
    private String secretKey = "GoXI+IeBDK0J+DOwUFb0vsG+d8UDl4lA6pil1znN"; // Reemplaza con tu Secret Access Key
    private String sessionToken = "IQoJb3JpZ2luX2VjEHoaCXVzLXdlc3QtMiJHMEUCIChTManmnM1UnZjq+Ks0LQSZj1oLCsz6hui/8xDn74TeAiEAjLkVdufazcKv7ikLpXi0NbH9sJGP+Bt65QwD9eh4SXgqvQII8v//////////ARAAGgw5OTIzODI2NjYwNjMiDFWmv2UuCqR0k4U9siqRAj4IJ8M7mzjt6BCjiyuv7ECyh/pmaS8BO6/jLQ/CWSwWpN1lyzKFANn7NusDovmiHmv+j7KhoEFOgA0O3P9jXT0puKIyqKuocuk55+k0K7UhS1AEx4lRaDMxUswIlWsrdPpIdKtnlbE22DWgd9GYorUzsQMc8xkafqi+cUfYvYxDX4boX51AiPUg1ttmDhu7q9HutqU7mLz6zhIN8jBOpuUOieqaqshfEazqzMLLfoFbHFPgBybPa0bOdBHVuAtCgQ3mi1SYmeWql7UoKnGE5mQpR9w+9q5OGyby23mgfsAjEQUUq2LQkmktfMe7xrOpkx2gxzVtgflNbB9aFXFlP+gW7MusN/KcqpLOY/osxvcjvzDqg6S5BjqdAZbFSCrE0Md61ANIPOO8tgXkjlrnK990vIjVgF9sSqcrVFf56opcTT2ket+9Q2/7xqV6ABGgUbQL3TwKZfhkwI7m/pYWQLjdCAzIiwd91sNvshwinWSt5bH65/fg3AbLYmCj8i2WASvk6JiyLrguSRginRF5TfL2VMgl0YTmrDcQ1u07GSrESUeGOx+HEt+Ncjq2Pfq7nx3wXMDtHXk=";
    
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

    public void insertFarmacia(String id, String nombre, String direccion, String propietario_id) {
        try {
            // Obtener la tabla
            Table table = dynamoDB.getTable(tableNameFarmacia);

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

    public void insertLaboratorio(String id_lab, String dir, String contact, String namel) {
        try {
            Table table = dynamoDB.getTable("Laboratorio");
            Item item = new Item().withPrimaryKey("id_laboratorio", id_lab).withString("Direccion", dir).withString("Contacto", contact).withString("Nombre", namel);
            table.putItem(item);
            System.out.println("Laboratorio insertado correctamente");
            JOptionPane.showMessageDialog(null, "Laboratorio insertado correctamente");
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
		Table table = dynamoDB.getTable("Cliente");
		Item item = new Item().withPrimaryKey("id_cliente", id_cliente).withString("Nombre", nom);
		table.putItem(item);
		System.out.print("Cliente insertado exitosamente");
                JOptionPane.showMessageDialog(null, "Cliente insertado exitosamente.\nSu ID es: " + id_cliente);
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
    
    public List<String> obtenerDatosCombinados() {
        List<String> owners = new ArrayList<>();

        try {
            // Obtener datos de la tabla Farmaceutico
            Table tableFarmaceutico = dynamoDB.getTable(tableNameFarmaceutico);
            ScanSpec scanSpecFarmaceutico = new ScanSpec();
            Iterator<Item> iteratorFarmaceutico = tableFarmaceutico.scan(scanSpecFarmaceutico).iterator();

            while (iteratorFarmaceutico.hasNext()) {
                Item item = iteratorFarmaceutico.next();
                String idFarmaceutico = item.getString("id_farmaceutico");
                String nombreFarmaceutico = item.getString("Nombre");
                // Agregar al listado con un formato "ID - Nombre"
                owners.add(idFarmaceutico + "-" + nombreFarmaceutico + " (Farmaceutico)");
            }

            // Obtener datos de la tabla Propietario
            Table tablePropietario = dynamoDB.getTable(tableNamePropietario);
            ScanSpec scanSpecPropietario = new ScanSpec();
            Iterator<Item> iteratorPropietario = tablePropietario.scan(scanSpecPropietario).iterator();

            while (iteratorPropietario.hasNext()) {
                Item item = iteratorPropietario.next();
                String idPropietario = item.getString("id_propietario");
                String nombrePropietario = item.getString("Nombre");
                // Agregar al listado con un formato "ID - Nombre"
                owners.add(idPropietario + "-" + nombrePropietario + " (Propietario)");
            }

        } catch (Exception e) {
            System.out.println("No se pudieron recuperar los datos: " + e.getMessage());
        }

        return owners;
    }

}
