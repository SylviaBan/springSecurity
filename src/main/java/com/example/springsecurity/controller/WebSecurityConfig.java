package com.example.springsecurity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Activation de la config personnalisée de la sécurité
public class WebSecurityConfig {

    // Initialisation d'une instance de Bcrypt pour hacher les mots de passe
    // à réutiliser avec Autowire
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Définition des filtres sur les URL
    // Accès en fonction d'un état authentifié ou non
    // Accès en fonction de l'user authentifié (désactivé, expiré...)
    // Accès en fonction des rôles

    // Mise en place du filtre : le comportement de la sécurité envers les différentes urls
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        // désactive la gestion des en-têtes CORS au sein du spring Security
        http
                .cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                        httpSecurityCorsConfigurer.disable();
                    }
                });
        // désactivation du CSRF (Cross-Site Request Forgery)
        http.csrf(new Customizer<CsrfConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
                httpSecurityCsrfConfigurer.disable();
            }
        });
        // Confguration des règles d'authorisation conernant les requêtes HTTP
        http.authorizeHttpRequests(requests -> {
            requests
                    // Toutes les requetes HTTP / api/users sint autorisées partout pour tout le monde (authentifié ou pas)
                    .requestMatchers("/api/users").permitAll()
                    // toutes les autres requêtes HTTP nécessitent une authentification
                    .anyRequest().authenticated();
        });

        // Le management de la session : Srpign ne créera aucune session côté serveur
        // Inutile lors d'une config en RESTful
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Filtre avant les traitement par le filtre UsernamePasswordAuthenticationFilter.
        // Objectif : gérer l'authentification basée sur le JWT reçu dans les en-têtes des requêtes
        // Le filtre UsernamePasswordAuthenticationFilter est un filtre de base.
        // Il est exécuté pour gérer l'authentif by username & password
        // Le filtre est personnalisé ! n'existe pas.
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
     AuthJwtTokenFilter authenticationJwtTokenFilter() {
        return new AuthJwtTokenFilter();
    }
}
