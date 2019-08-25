package ru.geracimov.otus.spring.hw18webflux.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;

import java.time.LocalDate;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> //, AuthorRepositoryCustom
{

    @Query("{ 'name' : {$regex: ?0, $options: 'i'} }")
    Flux<Author> findAllByName(String name);

    Flux<Author> findAllByBirthBetween(LocalDate from, LocalDate to);

    Mono<Author> findAuthorById(String id);

    Flux<Author> findAllBy();

}
