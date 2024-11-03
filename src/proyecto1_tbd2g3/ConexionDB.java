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
    private String accessKey = "ASIA6ODU5YVHSMOEHMV7"; // Reemplaza con tu Access Key ID
    private String secretKey = "/oA6d8mU/ex7W/taezioD/QyZpVuwDpUiUlVvm55"; // Reemplaza con tu Secret Access Key
    private String sessionToken = "IQoJb3JpZ2luX2VjEGYaCXVzLXdlc3QtMiJGMEQCIA+g5bPBh4cTFcDOEWhgoQikx97y21J1BVfg7VmdpFxQAiBTJuJqXJ+dwLZ3yEET/etI0ZcqMnIwev3kR1FK51iFJCq9Agjf//////////8BEAAaDDk5MjM4MjY2NjA2MyIMTEh0u8nDgbwfYrOeKpEC1Qg0Ujl+dV87ChLdMNm9X+Byz/BMI3TwDyqcuAecJyji4P+PDtSuPFgj6D0h5ez1c8B6XZ7F0c75RA3oHAAXgOOUIk5Tze1AZPIAAy698qtcnGVGMlPToyMbM3CJzSYXRRHRgx5XTdwDlnf5DHYtR56otVUwR4vbRL8OUNt3DoXGYc3sxNbioG95LtsUj6hI3GUI8Cojf2bX+pXSWSqBnOcf8XMw4s8Y85da+ysD9/xMF/Yl2QeQXeH4Im96qAT/t5tiD8HfzB+dzSMv30zxW1yyoDDh2xnmL8gDXV2ISw6+cdeTxklcCKRhonUmuK/EZ8HaAaP+xZsIdjupJVxjM+4K4pE807hkZQ1+c9wch3COMNPfn7kGOp4BRaUC5p2vfguWK29Z7G1QJcA5dPZywASjTRxZ2uJBGd/33TnMSf4S/xTJ8VPWmgmNGWTv77TO2jgOAdwZ9GErAHnr00xOXtHHuLDCmIQEcuyNXYKjHZ563uVmNoNuL3t8P+kh3JbmQJQNswaneBxLAB3Vc6MV/Z2v4Hol9sNvLcCCG+3uYhSx2dGdPb7yoI+EnN9bJxM7bL4cVAgSbvw=";
    
    public void Conectar(){
        // Crear las credenciales con el token de sesión
        BasicSessionCredentials awsCreds = new BasicSessionCredentials(accessKey, secretKey, sessionToken);

        // Inicializar el cliente de DynamoDB
        client = AmazonDynamoDBClientBuilder.standard()
                .withRegion("us-east-1") // Cambia a tu región
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        dynamoDB = new DynamoDB(client);
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