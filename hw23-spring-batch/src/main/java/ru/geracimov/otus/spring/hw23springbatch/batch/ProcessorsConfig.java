package ru.geracimov.otus.spring.hw23springbatch.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.AuthorMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.BookMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.GenreMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.AuthorH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.BookH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.GenreH2;

@Configuration
@RequiredArgsConstructor
public class ProcessorsConfig {
    private final Converter<BookH2, BookMongo> bookConverter;
    private final Converter<AuthorH2, AuthorMongo> authorConverter;
    private final Converter<GenreH2, GenreMongo> gengeConverter;

    @Bean
    public ItemProcessor<BookH2, BookMongo> bookItemProcessor() {
        return bookConverter::convert;
    }

    @Bean
    public ItemProcessor<AuthorH2, AuthorMongo> authorItemProcessor() {
        return authorConverter::convert;
    }

    @Bean
    public ItemProcessor<GenreH2, GenreMongo> genreItemProcessor() {
        return gengeConverter::convert;
    }

}
