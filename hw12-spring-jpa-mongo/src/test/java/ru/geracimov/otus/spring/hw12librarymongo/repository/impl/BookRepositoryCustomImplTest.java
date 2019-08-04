package ru.geracimov.otus.spring.hw12librarymongo.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.geracimov.otus.spring.hw12librarymongo.AbstractRepositoryTest;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Review;
import ru.geracimov.otus.spring.hw12librarymongo.repository.BookRepository;
import ru.geracimov.otus.spring.hw12librarymongo.repository.GenreRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("BookRepositoryCustomImplTest должен")
class BookRepositoryCustomImplTest extends AbstractRepositoryTest {

    @Autowired
    BookRepository bRepo;
    @Autowired
    GenreRepository gRepo;


    @Test
    @DisplayName("корректно добавлять жанр к книге по id")
    void addGenreToBook() {
        val genre = gRepo.findAll()
                         .get(0);
        val book = bRepo.findAll()
                        .get(0);
        val genresSize = book.getGenres()
                             .size();

        bRepo.addGenreToBook(genre.getId(), book.getId());
        val bookUpdatedOpt = bRepo.findById(book.getId());

        assertThat(bookUpdatedOpt.isPresent())
                .isTrue();
        assertThat(bookUpdatedOpt.get()
                                 .getGenres())
                .hasSize(genresSize + 1)
                .contains(genre);
    }

    @Test
    @DisplayName("корректно добавлять отзыв к книге по id")
    void addReviewToBook() {
        val book = bRepo.findAll()
                        .get(0);

        assertThat(book.getReviews()).hasSize(1);
        val newReview = new Review("newReviewverName", LocalDateTime.of(2019, 7, 1, 0, 0), "newTestTextReview");
        bRepo.addReviewToBook(newReview, book.getId());
        val bookUpdated = bRepo.findById(book.getId())
                               .orElseThrow(IllegalArgumentException::new);
        assertThat(bookUpdated.getReviews()).hasSize(2)
                                            .contains(newReview);
    }
}