package ru.geracimov.otus.spring.hw12librarymongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.repository.AuthorRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;


@EnableMongoRepositories
@SpringBootApplication
public class LibraryMongoApp {

    public static void main(String[] args) {
        SpringApplication.run(LibraryMongoApp.class);
    }

    @PostConstruct
    public void ssss(AuthorRepository repo) {
        repo.save(new Author("asdasd", LocalDate.now()));
    }
}
