package com.santi.spring_boot_microservice_compra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Value("${service.security.secure-key-username}")
    private String SECURE_KEY_USERNAME;

    @Value("${service.security.secure-key-password}")
    private String SECURE_KEY_PASSWORD;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
            AuthenticationManagerBuilder.class
        );

        authenticationManagerBuilder.inMemoryAuthentication()
        .withUser(SECURE_KEY_USERNAME)
        .password(new BCryptPasswordEncoder().encode(SECURE_KEY_PASSWORD))
        .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"))
        .and()
        .passwordEncoder(new BCryptPasswordEncoder());

        http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/**").hasRole("ADMIN") // Define la ruta y rol requerido
            .anyRequest().authenticated()
        )
        .httpBasic(httpBasic -> {})
        .exceptionHandling(exception -> exception
            .accessDeniedHandler((request, response, exceptionHandler) -> {
                response.sendRedirect("https://vaxidrez.com");
            })
        );

        return http.build();

    }
}
