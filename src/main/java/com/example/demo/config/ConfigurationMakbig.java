package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.service.checkAuth;
@Configuration
@EnableWebSecurity //why
public class ConfigurationMakbig {
	
	 @Autowired
	    private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(soofiya -> soofiya.disable());
        
        httpSecurity.httpBasic(Customizer.withDefaults());
        
            
    
      
        
      
       
        httpSecurity.authorizeHttpRequests(Request ->
        Request.requestMatchers("/register","/","/login","/css/**").permitAll()
        .anyRequest().authenticated());
        
        
        httpSecurity
        .formLogin(formLogin -> 
        formLogin
            .loginPage("/login") 
            .loginProcessingUrl("/perform_login") 
            .defaultSuccessUrl("/home", true) 
            .failureUrl("/login?error=true") 
            .permitAll()
    )
    .logout(logout -> 
        logout
            .logoutUrl("/perform_logout")
            .logoutSuccessUrl("/login?logout=true")
            .permitAll()
            
            
    );
        
        
        // Return the SecurityFilterChain object
        return httpSecurity.build();
	}
	


	
	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new checkAuth();
	    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService( userDetailsService);
		authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return authenticationProvider;
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
		
	}
	
	


	
	
	
	
	
}
