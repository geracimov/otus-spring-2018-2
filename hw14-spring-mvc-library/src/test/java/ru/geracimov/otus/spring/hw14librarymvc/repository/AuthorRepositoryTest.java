package ru.geracimov.otus.spring.hw14librarymvc.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.geracimov.otus.spring.hw14librarymvc.domain.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveAuthorTest() {
        String name = "name";
        LocalDate birth = LocalDate.now();
        Author author = authorRepository.save(new Author(name, birth));

        assertNotNull(author.getId());
        assertEquals(name, author.getName());
        assertEquals(birth, author.getBirth());
    }

    @Test
    void findAllAuthorTest() {
        List<Author> authorList = authorRepository.findAll();
        assertThat(authorList).hasSize(5)
                              .doesNotContainNull();
        assertThat(authorList.stream()
                             .map(Author::getName)
                             .collect(Collectors.toList())).contains("Лев Толской")
                                                           .contains("Федор Достоевский")
                                                           .contains("Антон Чехов")
                                                           .contains("Иван Гончаров")
                                                           .contains("Иван Тургенев");
 

    }
}