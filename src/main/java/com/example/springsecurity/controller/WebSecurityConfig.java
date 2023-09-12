package com.example.springsecurity.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity // Activation de la config personnalisée de la sécurité
public class WebSecurityConfig {
    // Définition des filtres sur les URL
    // Accès en fonction d'un état authentifié ou non
    // Accès en fonction de l'user authentifié (désactivé, expiré...)
    // Accès en fonction des rôles


}
