package ru.geracimov.otus.spring.hw25springintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class SpringIntegrationApp {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationApp.class);
    }

}
