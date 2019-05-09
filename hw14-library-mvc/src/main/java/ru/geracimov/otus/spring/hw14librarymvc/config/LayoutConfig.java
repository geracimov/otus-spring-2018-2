package ru.geracimov.otus.spring.hw14librarymvc.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LayoutConfig {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
