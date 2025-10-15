package com.soundinstantz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main Spring Boot Application Class
 * 
 * This is the entry point for the English Learning Platform backend application.
 * The application follows Domain-Driven Design (DDD) principles and provides
 * AI-powered English learning capabilities.
 */
@SpringBootApplication
@EnableJpaAuditing
public class SoundInstantApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SoundInstantApplication.class, args);
    }
}
