package com.senai.apimuralvagas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, "/auth/cadastro/admin", "/auth/cadastro/empresa", "/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/empresa/**").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/admin/**").hasRole("ADMIN")
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**",  "/swagger-ui.html", "/swagger-resources/**", "/webjars/**" ).permitAll()
                .requestMatchers(HttpMethod.DELETE, "/empresa/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/empresa/**").hasRole("EMPRESA")
                .requestMatchers(HttpMethod.POST, "/vagas/**").hasRole("EMPRESA")
                .requestMatchers(HttpMethod.GET, "/vagas/**").permitAll()
                .anyRequest().permitAll() 
            )
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}

