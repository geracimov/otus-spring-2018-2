package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.AuthorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@EntityScan("ru.geracimov.otus.spring.hw10libraryjpa.domain")
@EnableJpaRepositories("ru.geracimov.otus.spring.hw10libraryjpa.repository")
@ComponentScan("ru.geracimov.otus.spring.hw10libraryjpa")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class AuthorRepositoryJpaTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void create() {
        Author author = new Author("name", LocalDate.now());
        assertThatCode(() ->
                authorRepository.create(author)
        ).doesNotThrowAnyException();
    }

    @Test
    void createAndRead() {
        Author author = authorRepository.read(Author.class, UUID.fromString("a3057eca-556e-11e9-8647-d663bd873d93"));
        assertThat(author).hasFieldOrPropertyWithValue("name", "author3");
    }

    @Test
    void update() {
        String newName = "newName";
        Author author = authorRepository.read(Author.class, UUID.fromString("a3057b96-556e-11e9-8647-d663bd873d93"));
        author.setName(newName);
        authorRepository.update(author);
        Author authorUpd = authorRepository.read(Author.class, UUID.fromString("a3057b96-556e-11e9-8647-d663bd873d93"));
        assertThat(authorUpd.getName()).isEqualTo(newName);
    }

    @Test
    void delete() {
        List<Author> authors = authorRepository.getAllAuthors();
        Author first = authors.get(0);
        authorRepository.delete(first);
        List<Author> authors2 = authorRepository.getAllAuthors();

        assertThat(authors2.size()).isEqualTo(authors.size() - 1);
        assertThat(authorRepository.read(Author.class, first.getId())).isNull();
    }

    @Test
    void getAllAuthors() {
        assertThat(authorRepository.getAllAuthors()).size().isEqualTo(3);
    }
}