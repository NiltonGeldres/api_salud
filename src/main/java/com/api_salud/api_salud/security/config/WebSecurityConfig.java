/**
 * 
 */
package com.api_salud.api_salud.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.api_salud.api_salud.request.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
//import static org.springframework.security.config.Customizer.withDefaults;
/**
 * @author ngeldres
 *
 */

//
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired 
	private UserDetailsService us;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;	

    @Autowired
	private JwtRequestFilter jwtRequestFilter;

    

	/*
	@Bean
	public BCryptPasswordEncoder  passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(us).passwordEncoder(passwordEncoder);
	}
	
/*	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.userDetailsService(us).passwordEncoder(passwordEncoder());
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
        .csrf().disable() // (2)
        .authorizeRequests()
        .antMatchers("/auth").permitAll()
        .antMatchers("/signin").permitAll()
        .antMatchers("/entidad_por_nombre").permitAll()
        .antMatchers("/usuarioDatosGlobales").permitAll()
        .antMatchers("/admin").hasAuthority("Administrador")
//        .antMatchers("/adicionales").hasAuthority("Administrador")
        .anyRequest().authenticated()
        .and().cors()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
}


