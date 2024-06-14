package br.com.innovix.drivers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.innovix.frameworks.repository.jpa")
public class DatabaseConfig {
}
