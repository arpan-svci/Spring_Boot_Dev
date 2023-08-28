package com.luv2code.springboot.CrudDemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails arpan= User.builder()
                .username("arpan")
                .password("{noop}arpan@123")
                .roles("EMPLOYEE")
                .build();
        UserDetails rahul= User.builder()
                .username("rahul")
                .password("{noop}abcd")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails satabda= User.builder()
                .username("satabda")
                .password("{noop}abcd")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(arpan,rahul,satabda);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(
                configurer->
                        configurer
                                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT,"/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
        );
        // use Http basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable CSRF
        // in general, not required for stateless REST APIs that use POST, PUT,DELETE and/or PATCH
        http.csrf(csrf->csrf.disable());
        return http.build();
    }
}
