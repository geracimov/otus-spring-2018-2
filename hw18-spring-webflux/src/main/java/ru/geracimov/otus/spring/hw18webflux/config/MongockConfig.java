package ru.geracimov.otus.spring.hw18webflux.config;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongockConfig {

    private static final String CHANGELOGS_PACKAGE = "ru.geracimov.otus.spring.hw18webflux.changelogs";
    @Value("${spring.data.mongodb.database}")
    private String dbName;

    @Bean
    public Mongock mongock(MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, dbName, CHANGELOGS_PACKAGE)
                .setLockQuickConfig()
                .build();
    }
}
