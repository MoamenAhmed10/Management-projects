package com.moamen.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.moamen")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public DreamCompany dreamCompany() {
        return new DreamCompany("Dream Company");
    }
}
