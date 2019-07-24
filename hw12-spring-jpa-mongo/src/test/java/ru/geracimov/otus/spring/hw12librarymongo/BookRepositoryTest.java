package ru.geracimov.otus.spring.hw12librarymongo;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geracimov.otus.spring.hw12librarymongo.repository.BookRepository;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepository должен ")
class BookRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private BookRepository bRepo;

    @DisplayName(" возвращать корректный список авторов")
    @Test
    void shouldReturnCorrectKnowledgeList() {
        val books = bRepo.findAll();
        books.forEach(b -> assertThat(b.getAuthors()).isNotNull()
                                                     .isOfAnyClassIn(ArrayList.class)
                                                     .hasSize(1)
        );
    }

}
