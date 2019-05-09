package ru.geracimov.otus.spring.hw10libraryjpa.domain;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class AuthorTest {

    @Autowired
    TestEntityManager em;

    @Test
    void saveAndGet() {
        Author a = new Author("name", LocalDate.now());
        UUID uuid = (UUID) em.persistAndGetId(a);
        System.out.println(uuid);
        Author aDb = em.find(Author.class, uuid);
        assertThat(aDb).isEqualTo(a);
    }

}