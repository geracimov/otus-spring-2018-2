package ru.geracimov.otus.spring.hw23springbatch.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.AuthorMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.BookMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.GenreMongo;

@Configuration
@RequiredArgsConstructor
public class WritersConfig {
    private final MongoTemplate mt;

    @Bean
    public ItemWriter<BookMongo> bookItemMongoWriter() {
        return new MongoItemWriterBuilder<BookMongo>().collection("Book")
                                                      .template(mt)
                                                      .build();
    }

    @Bean
    public ItemWriter<AuthorMongo> authorItemMongoWriter() {
        return new MongoItemWriterBuilder<AuthorMongo>().collection("Author")
                                                        .template(mt)
                                                        .build();
    }

    @Bean
    public ItemWriter<GenreMongo> genreItemMongoWriter() {
        return new MongoItemWriterBuilder<GenreMongo>().collection("Genre")
                                                       .template(mt)
                                                       .build();
    }

}
