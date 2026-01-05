package com.api_salud.api_salud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("classpath:application.properties")
@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class})
@EnableTransactionManagement

public class ApiSaludApplication {
//extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApiSaludApplication.class, args);
	}

}
