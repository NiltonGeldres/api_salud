package com.api_salud.api_salud.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LimpiezaCitasTask {

    private static final Logger log = LoggerFactory.getLogger(LimpiezaCitasTask.class);
    private final JdbcTemplate jdbcTemplate;
    
    // Declaramos los llamados como finales para asegurar que se configuren una sola vez
    private SimpleJdbcCall procedureExpiracion;
    private SimpleJdbcCall procedureDepuracion;

    @Autowired
    public LimpiezaCitasTask(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        // Inicializamos los procedimientos una sola vez al arrancar la app
        this.procedureExpiracion = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_citas")
                .withProcedureName("citas_separadas_limpieza_expirados");

        this.procedureDepuracion = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("igm_citas")
                .withProcedureName("citas_separadas_depuracion_expiradas");
    }

    /**
     * Tarea 1: Mueve de PENDIENTE_PAGO a ANULADA X PAGO.
     * Se ejecuta cada 15 minutos (900,000 ms).
     */
    @Scheduled(fixedRate = 900000) 
    public void ejecutarExpiracionDePagoCitasSeparads() {
        try {
            log.info("Iniciando: Expiración de reservas pendientes...");
            procedureExpiracion.execute();
            log.info("Éxito: Reservas expiradas movidas a ANULADA X PAGO.");
        } catch (Exception e) {
            log.error("Error en tarea de expiración: {}", e.getMessage());
        }
    }

    /**
     * Tarea 2: Mueve de ANULADA X PAGO a DEPURADA.
     * Se ejecuta todos los días a las 12:00 AM (Medianoche).
     * Formato Cron: "segundo minuto hora día mes día-semana"
     */
   // @Scheduled(cron = "0 0 0 * * *") 
    @Scheduled(cron = "0 0 0 * * *", zone = "America/Lima")    
    public void ejecutarDepuracionCitasSeparadasExpiradas() {
        try {
            log.info("Iniciando: Depuración diaria de citas anuladas (Limpieza profunda)...");
            procedureDepuracion.execute();
            log.info("Éxito: Registros antiguos marcados como DEPURADA.");
        } catch (Exception e) {
            log.error("Error en tarea de depuración diaria: {}", e.getMessage());
        }
    }
}



/*
@Component // 1. Esto es obligatorio para que @Scheduled funcione
@EnableScheduling
public class LimpiezaCitasTask {

    @Autowired // 2. Inyectar el JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    // 3. El método debe ser public void y estar dentro de la clase
    @Scheduled(fixedRate = 900000) 
    public void ejecutarExpiracionDePagoCitasSeparads() {
        try {
            // Nota: SimpleJdbcCall es mejor instanciarlo una vez o aquí mismo
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("igm_citas")
                    .withProcedureName("citas_separadas_limpieza_expirados");
            
            jdbcCall.execute();
        } catch (Exception e) {
        }
    }
    
    // 3. El método debe ser public void y estar dentro de la clase
    @Scheduled(fixedRate = 900000) 
    public void ejecutarDepuracionCitasSeparadasExpiradas() {
        try {
            // Nota: SimpleJdbcCall es mejor instanciarlo una vez o aquí mismo
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("igm_citas")
                    .withProcedureName("citas_separadas_depuracion_expiradas");
            
            jdbcCall.execute();
        } catch (Exception e) {
        }
    }
    
}

*/