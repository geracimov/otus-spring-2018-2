package ru.geracimov.otus.spring.hw18webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;


@SpringBootApplication
@EnableWebFlux
@EnableReactiveMongoRepositories
public class LibraryWebFluxApp {

    public static void main(String[] args) {
        SpringApplication.run(LibraryWebFluxApp.class);
    }

}
