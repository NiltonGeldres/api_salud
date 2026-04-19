package com.api_salud.api_salud.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component // 1. Esto es obligatorio para que @Scheduled funcione
public class LimpiezaCitasTask {

    @Autowired // 2. Inyectar el JdbcTemplate
    private JdbcTemplate jdbcTemplate;

    // 3. El método debe ser public void y estar dentro de la clase
    @Scheduled(fixedRate = 60000) 
    public void ejecutarLimpieza() {
        try {
            // Nota: SimpleJdbcCall es mejor instanciarlo una vez o aquí mismo
            SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withSchemaName("igm_citas")
                    .withProcedureName("citas_separadas_limpieza_expirados");
            
            jdbcCall.execute();
        } catch (Exception e) {
        }
    }
}