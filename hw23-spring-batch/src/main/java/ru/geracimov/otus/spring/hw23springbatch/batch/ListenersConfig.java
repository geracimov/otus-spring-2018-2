package ru.geracimov.otus.spring.hw23springbatch.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ListenersConfig {

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info("Migration start...");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info("Migration finished.");
            }
        };
    }


    @Bean
    public ChunkListener chunkListener() {
        return new ChunkListener() {

            @Override
            public void beforeChunk(ChunkContext context) {
                log.info("Chunk  started (" + context.getStepContext()
                                                     .getStepExecution()
                                                     .getSummary() + ")");
            }

            @Override
            public void afterChunk(ChunkContext context) {
                log.info("Chunk  finished (" + context.getStepContext()
                                                      .getStepExecution()
                                                      .getSummary() + ")");
            }

            @Override
            public void afterChunkError(ChunkContext context) {
                log.info("Chunk error!");
            }
        };

    }
}
