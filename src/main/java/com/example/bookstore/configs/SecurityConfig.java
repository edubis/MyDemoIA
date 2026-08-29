package com.example.bookstore.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // habilita @PreAuthorize / @RolesAllowed
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita CSRF para API (se sua API for stateful com formulários, ajuste conforme necessário)
                .csrf(csrf -> csrf.disable())
                // Para API REST: sem sessão
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Autorização por endpoint
                .authorizeHttpRequests(auth -> auth
                        // permitir leitura pública por GET (exemplo)
                        .requestMatchers(HttpMethod.GET, "/graphic_card/**").permitAll()
                        // exigir ADMIN para demais operações sobre /graphic_card/**
                        .requestMatchers("/graphic_card/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // Habilita Basic (útil para testes com Postman)
                .httpBasic(Customizer.withDefaults())
                // (opcional) mantém formLogin para testes via navegador se desejar
                .formLogin(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Securely hashes passwords
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(encoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}