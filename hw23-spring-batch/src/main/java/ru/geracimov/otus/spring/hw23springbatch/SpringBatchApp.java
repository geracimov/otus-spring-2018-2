package ru.geracimov.otus.spring.hw23springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;


@SpringBootApplication
@EnableJpaRepositories
@EnableMongoRepositories
public class SpringBatchApp {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringBatchApp.class);
        org.h2.tools.Console.main(args);
    }

}
