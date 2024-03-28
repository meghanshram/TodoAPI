package com.springboot.myfirstwebapp.security;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration 
{
	@Bean
	public InMemoryUserDetailsManager createUserDetailsManager()
	{

		UserDetails userDetails1 = createNewUser("meg","ram");
		UserDetails userDetails2 = createNewUser("root","root");
		
		return new InMemoryUserDetailsManager(userDetails1,userDetails2);
	}

	private UserDetails createNewUser(String username,String password) {
		Function<String, String> passwordEncoder
				=input -> passwordEncoder().encode(input);
		UserDetails userDetails=User.builder()
								.passwordEncoder(passwordEncoder)
								.username(username)
								.password(password)
								.roles("USER","ADMIN")
								.build();
		return userDetails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	//custom config to enable h2 in our application
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		//mandatory as we are re configuring our spring security or these are auto implemented
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
		http.formLogin(withDefaults());
		
		//custom config
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		return http.build();
	}

}
