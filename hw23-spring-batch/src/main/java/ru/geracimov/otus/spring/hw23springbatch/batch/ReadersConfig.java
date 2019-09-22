package ru.geracimov.otus.spring.hw23springbatch.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.AuthorH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.BookH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.GenreH2;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class ReadersConfig {
    private int pageSize = 5;

    @Bean
    public ItemReader<BookH2> bookItemReader(EntityManagerFactory emf) throws Exception {
        JpaPagingItemReader<BookH2> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("select b from Book b left join fetch b.authors a left join fetch b.genres g");
        reader.setPageSize(pageSize);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    public ItemReader<AuthorH2> authorItemReader(EntityManagerFactory emf) throws Exception {
        JpaPagingItemReader<AuthorH2> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("select a from Author a");
        reader.setPageSize(pageSize);
        reader.afterPropertiesSet();
        return reader;
    }

    @Bean
    public ItemReader<GenreH2> genreItemReader(EntityManagerFactory emf) throws Exception {
        JpaPagingItemReader<GenreH2> reader = new JpaPagingItemReader<>();
        reader.setEntityManagerFactory(emf);
        reader.setQueryString("select g from Genre g");
        reader.setPageSize(pageSize);
        reader.afterPropertiesSet();
        return reader;
    }

}
