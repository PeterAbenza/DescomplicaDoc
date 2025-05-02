package com.descomplicadoc.descomplicadoc.configs;
	
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
	
@Configuration
public class SecurityConfig {
	
// SecurityFilterChain para configurar regras de acesso
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	   http
	      .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/", "/uploadPDF", "/register", "/criar-conta", "/css/**", "/js/**", "/img/**", "/fonts/**").permitAll()
	        .anyRequest().authenticated()  
	       )
	      .formLogin(form -> form
	        .loginPage("/login")  
	        .defaultSuccessUrl("/", true)  
	        .permitAll()  
	       )
	     .logout(logout -> logout
	        .logoutUrl("/logout")  
	        .logoutSuccessUrl("/login?logout")  
	        .permitAll()  
	     );
	        
	    return http.build();
	}
}
