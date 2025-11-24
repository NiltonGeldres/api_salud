package com.api_salud.atencionmedica.config;



import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * Configuración explícita para la gestión de transacciones JDBC.
 * Esto es necesario cuando se desactiva JPA/Hibernate para asegurar que
 * la anotación @Transactional sepa qué PlatformTransactionManager utilizar.
 */
@Configuration
public class JdbcConfig {

    /**
     * Define el bean del gestor de transacciones específico para el DataSource.
     * Spring usará este bean (DataSourceTransactionManager) para todas las
     * anotaciones @Transactional.
     * * @param dataSource La fuente de datos autoconfigurada por Spring Boot.
     * @return El gestor de transacciones basado en JDBC.
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}