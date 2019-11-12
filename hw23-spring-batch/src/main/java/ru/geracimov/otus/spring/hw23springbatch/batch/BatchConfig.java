package ru.geracimov.otus.spring.hw23springbatch.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowJob;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.AuthorMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.BookMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.GenreMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.AuthorH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.BookH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.GenreH2;

@Slf4j
@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
@Import(ProcessorsConfig.class)
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ChunkListener chunkListener;
    private final JobExecutionListener jobExecutionListener;
    private int chunkSize = 5;

    @Bean
    public Job migrateBooksJob(Step migrateBooks, Step migrateAuthors, Step migrateGenres) {
        FlowJob job = (FlowJob) jobBuilderFactory.get("migrateBooksJob")
                                                 .incrementer(new RunIdIncrementer())
                                                 .flow(migrateAuthors)
                                                 .next(migrateGenres)
                                                 .next(migrateBooks)
                                                 .end()
                                                 .listener(jobExecutionListener)
                                                 .build();
        job.setRestartable(true);
        return job;
    }

    @Bean
    public Step migrateAuthors(ItemReader<AuthorH2> authorItemReader,
                               ItemProcessor<AuthorH2, AuthorMongo> authorItemProcessor,
                               ItemWriter<AuthorMongo> authorItemWriter) {
        return stepBuilderFactory.get("migrateAuthors")
                .<AuthorH2, AuthorMongo>chunk(chunkSize)
                .reader(authorItemReader)
                .processor(authorItemProcessor)
                .writer(authorItemWriter)
                .allowStartIfComplete(true)
                .listener(chunkListener)
                .build();
    }

    @Bean
    public Step migrateGenres(ItemReader<GenreH2> genreItemReader,
                              ItemProcessor<GenreH2, GenreMongo> genreItemProcessor,
                              ItemWriter<GenreMongo> genreItemWriter) {
        return stepBuilderFactory.get("migrateGenres")
                .<GenreH2, GenreMongo>chunk(chunkSize)
                .reader(genreItemReader)
                .processor(genreItemProcessor)
                .writer(genreItemWriter)
                .allowStartIfComplete(true)
                .listener(chunkListener)
                .build();
    }

    @Bean
    public Step migrateBooks(ItemReader<BookH2> bookItemReader,
                             ItemProcessor<BookH2, BookMongo> bookItemProcessor,
                             ItemWriter<BookMongo> bookItemWriter) {
        return stepBuilderFactory.get("migrateBooks")
                .<BookH2, BookMongo>chunk(chunkSize)
                .reader(bookItemReader)
                .processor(bookItemProcessor)
                .writer(bookItemWriter)
                .allowStartIfComplete(true)
                .listener(chunkListener)
                .build();
    }

}
