package com.api_salud.api_salud.security.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer { 
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
       .allowedOrigins( 
    		             "http://localhost:3000",
    		             "http://192.168.0.200:3000",
    		             "http://192.168.0.6:3000"
    		   )
        .allowedMethods( "GET","POST", "PUT","DELETE")
        .allowedHeaders("*") // <--- MUY IMPORTANTE
        .allowCredentials(true);
    
  }
  
}



