package com.example.loginassignment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.example.loginassignment.service.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
        http.userDetailsService(customUserDetailsService);
        
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/restricted").hasRole("MANAGER");
            authorize.anyRequest().authenticated();
        })
        .formLogin(form -> {
            form.loginPage("/login")
            .loginProcessingUrl("/processFormLogin")
            .defaultSuccessUrl("/")
            .permitAll();
        }).logout(logout -> {
            logout.logoutUrl("/logout")
            .permitAll();
        });
        
        return http.build();
    }
    }
    