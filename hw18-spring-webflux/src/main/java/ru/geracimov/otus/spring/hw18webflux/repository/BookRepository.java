package ru.geracimov.otus.spring.hw18webflux.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;
import ru.geracimov.otus.spring.hw18webflux.domain.Book;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;

public interface BookRepository extends ReactiveMongoRepository<Book, String>/*, BookRepositoryCustom */ {

    Flux<Book> findAllByGenresContaining(Mono<Genre> genre);

    Flux<Book> findAllByAuthorsContaining(Mono<Author> author);

    @Query("{'reviews.id': ?0}")
    Flux<Book> findAllByReviewsContaining(String id);


}
