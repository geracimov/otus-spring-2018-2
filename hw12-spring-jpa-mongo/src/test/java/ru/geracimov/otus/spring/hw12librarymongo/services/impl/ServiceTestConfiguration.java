package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan({
        "ru.geracimov.otus.spring.hw12librarymongo.services",
        "ru.geracimov.otus.spring.hw12librarymongo.events"})
@AutoConfigureDataMongo
@EnableMongoRepositories(basePackages = "ru.geracimov.otus.spring.hw12librarymongo.repository")
public class ServiceTestConfiguration {
}
