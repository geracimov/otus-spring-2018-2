package ru.geracimov.otus.spring.hw25springintegration.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.geracimov.otus.spring.hw25springintegration.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw25springintegration.repository.BookRepository;

@Configuration
@RequiredArgsConstructor
public class ActuatorConfig {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    @Bean
    public HealthIndicator AuthorsCountHealthIndicator() {
        return () -> {
            long authors = authorRepository.count();

            return (authors > 10
                    ? Health.outOfService()
                            .withDetail("Text", "Too many authors count!")
                    : Health.up())
                    .withDetail("authorCount", authors)
                    .build();
        };
    }

    @Bean
    public HealthIndicator BooksCountHealthIndicator() {
        return () -> {
            long books = bookRepository.count();

            return Health.status("I'M FINE")
                         .withDetail("bookCount", books)
                         .build();
        };
    }

}