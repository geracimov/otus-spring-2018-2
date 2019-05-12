package ru.geracimov.otus.spring.hw14librarymvc.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Author;
import ru.geracimov.otus.spring.hw14librarymvc.services.AuthorService;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    AuthorService authorService;


    @Test
    @Transactional
    void deleteExistingAuthor() {
        UUID testUUID = UUID.fromString(
                "a3055b8e-556e-11e9-8647-d663bd873d93");

        Optional<Author> before = authorService.getAuthorById(testUUID);
        assertThat(before).isNotNull();
        int beforeCount = authorService.getAllAuthors()
                                       .size();

        authorService.delete(testUUID);

        int afterCount = authorService.getAllAuthors()
                                      .size();

        assertThat(beforeCount).isGreaterThan(afterCount);
    }
}
