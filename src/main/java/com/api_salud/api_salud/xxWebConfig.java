package com.api_salud.api_salud;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;




//@Configuration
public class xxWebConfig {
	/*
	 @Bean(name = "db1")
	 @Primary
	 @ConfigurationProperties(prefix = "spring.datasource")
	 public DataSource dataSource() {
		  return  DataSourceBuilder.create().build();
	 }
	 
	 @Bean(name = "jdbcTemplate")
	 @Primary
	 public JdbcTemplate jdbcTemplate(@Qualifier("db1") DataSource ds) {
		 return new JdbcTemplate(ds);
	 }
*/
	
	/*	
	
	 @Bean(name = "db1")
	 @Primary
	 @ConfigurationProperties(prefix = "spring.datasource1")
	 public DataSource dataSource1() {
		  return  DataSourceBuilder.create().build();
	 }
	 @Bean(name = "jdbcTemplate")
	 @Primary
	 public JdbcTemplate jdbcTemplate(@Qualifier("db1") DataSource ds) {
		 return new JdbcTemplate(ds);
	 }





	 @Bean(name = "db2")
	 @ConfigurationProperties(prefix = "spring.second-db")
	 public DataSource dataSource2() {
	  return   DataSourceBuilder.create().build();
	 }

	 
	 @Bean(name = "jdbcTemplate2")
	 public JdbcTemplate jdbcTemplate2(@Qualifier("db2") DataSource ds2) {
	  return new JdbcTemplate(ds2);
	 }
*/
	 
}