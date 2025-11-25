package com.api_salud.api_salud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

// **CRUCIAL**: Carga el contexto completo de Spring Boot, incluyendo 'application.properties'
@SpringBootTest 
public class DatabaseConnectionTest {

    // Inyecta el DataSource que Spring Boot configuró usando Hikari y tu application.properties
    @Autowired
    private DataSource dataSource;
    
    // JdbcTemplate es útil para ejecutar consultas simples para verificar la conexión.
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Prueba si el DataSource ha sido inicializado correctamente.
     * Si no se inicializa, este test fallará al inyectar 'dataSource'.
     */
    @Test
    void testDataSourceConfiguration() {
        System.out.println("--- 1. Probando la Inyección del DataSource ---");
        assertNotNull(dataSource, "El DataSource no debe ser nulo. Esto indica un fallo de configuración en Spring Boot.");
        System.out.println("DataSource inyectado exitosamente: " + dataSource.getClass().getName());
    }

    /**
     * Prueba si es posible obtener una conexión física a la base de datos.
     * Si falla, el problema es de credenciales (usuario/password) o red (puerto/host/BD).
     */
    @Test
    void testPhysicalDatabaseConnection() {
        System.out.println("--- 2. Probando la Conexión Física a la BD ---");
        try (Connection connection = dataSource.getConnection()) {
            
            // Si llegamos aquí, la conexión es exitosa
            assertNotNull(connection, "La conexión debe ser exitosa y no nula.");
            assertTrue(connection.isValid(2), "La conexión debe ser válida (ping a la BD).");
            
            System.out.println("Conexión exitosa a la BD: " + connection.getMetaData().getURL());
            System.out.println("Usuario conectado: " + connection.getMetaData().getUserName());
            
        } catch (SQLException e) {
            System.err.println("¡ERROR GRAVE DE CONEXIÓN! Las credenciales, el host o el puerto son incorrectos.");
            System.err.println("Mensaje de error: " + e.getMessage());
            // Si hay una excepción SQL, el test falla y muestra el mensaje.
            fail("Fallo al conectar con la base de datos. Verifica tu application.properties: " + e.getMessage());
        }
    }

    /**
     * Prueba una consulta SQL simple para verificar que la BD responde.
     */
    @Test
    void testSimpleQuery() {
        System.out.println("--- 3. Probando una Consulta Simple (SELECT 1) ---");
        try {
            // Ejecuta una consulta trivial que siempre devuelve 1.
            Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            assertNotNull(result, "La consulta SELECT 1 no debe devolver un resultado nulo.");
            assertTrue(result == 1, "La consulta SELECT 1 debe devolver el valor 1.");
            System.out.println("Consulta simple ejecutada con éxito.");
        } catch (Exception e) {
            fail("Fallo al ejecutar una consulta simple. Conexión establecida pero consulta fallida: " + e.getMessage());
        }
    }
}