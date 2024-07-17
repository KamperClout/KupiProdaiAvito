package com.example.avito.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.avito.jpa")
public class JpaConfig {
    // Дополнительная конфигурация JPA, если необходимо
}