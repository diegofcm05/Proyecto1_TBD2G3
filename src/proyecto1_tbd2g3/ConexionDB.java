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
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.ScanOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.ScanSpec;
import java.io.IOException;
import net.spy.memcached.MemcachedClient;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
    private String accessKey = "ASIA6ODU5YVH7RH24LUO"; //Access Key ID
    private String secretKey = "IgAznshSUif48VNqj2KcPNFeXspbqvWxb2du8T1k"; //Secret Access Key
    private String sessionToken = "IQoJb3JpZ2luX2VjEHwaCXVzLXdlc3QtMiJGMEQCIC9weOLPMdLF65/eNdreR0YMMIYmUdNDdKGpcRd9LHQPAiBkjUtStH7MRH7Ly1LG0NyCqYbA8bZod0SL/uj8qCehLyq9Agj1//////////8BEAAaDDk5MjM4MjY2NjA2MyIMDrgqXXIELCf9VNVzKpECsZyj63lgP6ypRWcDaiVJgRDdagrIATG6XUQjpH6fX+gMJbUVb/jmMZPMczG2X14edOpZ+n+yNhm3YZeeKNRuXHbWQ+c6EZ1yiOlHqvIJW3NAZviJeaOSLosEsab8OIThf9MuPEx48fNgwkyRKmopQnb37QyrobEouh6AWKiaLzo8vLXnW/6qs0o8ZFG7JhnvU0+5TPQukuLtOUk5hoqe+DQRCtx39ik7O7VZmlYkLVjCPUl0yFeNHjKFh2C4XVTqOUCTQUqdao6ism5OPcNNE/u9FwWkRrk4u7rz/zKnqjuZ2+e9cxDr0Gem5q4NowV5/GryBBgiMK/UhvYZ9L4HUAlziy5N3XZLFVpma35HnvIAMKnUpLkGOp4BOSLOooDTZ0EJkgAqM0BPZR4wcl7C6y7UfqymATmjpISr4/tV19Pdb916oV+Icm0U8oaIXqrmiuYx0xfr4YBaASVMY97elEABTar0ldBhfM9fpxQ/u69KyzNnEUfO0QtzvX7SGJwbhexyFaK2hi+yz7l60OoHWepur8ELwUKZ3mjPD7wTND4R7mi7IPq68fXWqN4/cf0dGeCzoi64WFQ=";
    
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

    public void insertProduct(String id, String nombre,String fabricante, String family, String owner, int precio_Coste, int precio_Venta, int unidades, boolean subvencionado, String idfarmacia) {
        try {
            // Obtener la tabla
            Table table = dynamoDB.getTable("Producto");

            // Crear un nuevo ítem
            Item item = new Item()
                    .withPrimaryKey("id_producto", id)
                    .withString("Nombre", nombre)
				.withString("Fabricante", fabricante)
				.withString("Familia", family)
                                .withString("id_farmacia", idfarmacia)
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
    
    public List<Item> obtenerDatosFarmacia() {
        List<Item> itemsList = new ArrayList<>();
        try {
            Table table = dynamoDB.getTable("Farmacia");
            Iterator<Item> iterator = table.scan(new ScanSpec()).iterator();
            while (iterator.hasNext()) {
                itemsList.add(iterator.next());
            }
        } catch (Exception e) {
            System.out.println("Error al obtener datos de Farmacia: " + e.getMessage());
        }
        return itemsList;
    }
    
    public void llenarJTableFarmacias(JTable table) {
        List<Item> farmaciaList = obtenerDatosFarmacia();
        
        // Crear el modelo de tabla con columnas específicas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Farmacia");
        model.addColumn("Dirección");
        model.addColumn("ID Propietario");
        model.addColumn("Nombre");

        // Agregar los datos de cada farmacia al modelo
        for (Item item : farmaciaList) {
            String idFarmacia = item.getString("id_farmacia");
            String direccion = item.getString("Direccion");
            String idPropietario = item.getString("ID_Propietario");
            String nombre = item.getString("Nombre");

            model.addRow(new Object[]{idFarmacia, direccion, idPropietario, nombre});
        }

        // Asignar el modelo al JTable
        table.setModel(model);
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
    
    public void poblarComboBoxFarmacia(JComboBox<String> comboBox) {
        // Limpia el ComboBox para evitar duplicados
        comboBox.removeAllItems();

        // Obtiene la tabla "Farmacia"
        Table table = dynamoDB.getTable("Farmacia");

        try {
            // Escanea la tabla "Farmacia"
            for (Item item : table.scan(new ScanSpec())) {
                // Obtiene los atributos "id_farmacia" y "Nombre"
                String idFarmacia = item.getString("id_farmacia");
                String nombreFarmacia = item.getString("Nombre");

                // Añade al ComboBox el texto formateado "id_farmacia - Nombre"
                if (idFarmacia != null && nombreFarmacia != null) {
                    comboBox.addItem(idFarmacia + "-" + nombreFarmacia);
                }
            }
            System.out.println("ComboBox de farmacias poblado correctamente.");
        } catch (Exception e) {
            System.err.println("No se pudo poblar el ComboBox de farmacias: " + e.getMessage());
        }
    }
    
    public void poblarComboBoxLaboratorio(JComboBox<String> comboBox) {
        // Limpia el ComboBox para evitar duplicados
        comboBox.removeAllItems();

        // Obtiene la tabla "Laboratorio"
        Table table = dynamoDB.getTable("Laboratorio");

        try {
            // Escanea la tabla "Laboratorio"
            for (Item item : table.scan(new ScanSpec())) {
                // Obtiene los atributos "id_laboratorio" y "Nombre"
                String idLaboratorio = item.getString("id_laboratorio");
                String nombreLaboratorio = item.getString("Nombre");

                // Añade al ComboBox el texto formateado "id_laboratorio - Nombre"
                if (idLaboratorio != null && nombreLaboratorio != null) {
                    comboBox.addItem(idLaboratorio + "-" + nombreLaboratorio);
                }
            }
            System.out.println("ComboBox de laboratorios poblado correctamente.");
        } catch (Exception e) {
            System.err.println("No se pudo poblar el ComboBox de laboratorios: " + e.getMessage());
        }
    }
    
    public void cargarProductosEnTabla(JTable table) {
        String tableName = "Producto";
        Table productoTable = dynamoDB.getTable(tableName);

        // Modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(new String[] {
            "ID Producto", "Fabricante", "Familia", "ID Farmacia", "Nombre", 
            "Precio Coste", "Precio Venta", "Subvencionado", "Unidades"
        }, 0);

        try {
            // Escaneo de la tabla de productos
            ScanSpec scanSpec = new ScanSpec();
            ItemCollection<ScanOutcome> items = productoTable.scan(scanSpec);
            Iterator<Item> iterator = items.iterator();

            // Iterar sobre los items y agregar a la tabla
            while (iterator.hasNext()) {
                Item item = iterator.next();
                
                Object[] rowData = new Object[] {
                    item.getString("id_producto"),
                    item.getString("Fabricante"),
                    item.getString("Familia"),
                    item.getString("id_farmacia"),
                    item.getString("Nombre"),
                    item.getNumber("Precio_Coste"),
                    item.getNumber("Precio_Venta"),
                    item.getBoolean("Subvencionado"),
                    item.getNumber("Unidades")
                };
                
                model.addRow(rowData);
            }

            // Asignar el modelo a la tabla
            table.setModel(model);

        } catch (Exception e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

}
