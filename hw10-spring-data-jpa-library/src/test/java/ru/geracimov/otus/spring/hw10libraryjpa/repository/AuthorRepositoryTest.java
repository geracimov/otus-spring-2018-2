package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;

import java.time.LocalDate;

@DataJpaTest
@EntityScan({"ru.geracimov.otus.spring.hw10libraryjpa.domain"})
@EnableJpaRepositories({"ru.geracimov.otus.spring.hw10libraryjpa.repository"})
@Transactional(
        propagation = Propagation.NOT_SUPPORTED
)
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository repository;

    public AuthorRepositoryTest() {
    }

    @Test
    public void testAuthorMapping() {
        Author author = new Author("name", LocalDate.now());
        Author authorDb = this.repository.save(author);
        ((ObjectAssert) Assertions.assertThat(author).isNotNull()).isEqualTo(authorDb);
    }
}
