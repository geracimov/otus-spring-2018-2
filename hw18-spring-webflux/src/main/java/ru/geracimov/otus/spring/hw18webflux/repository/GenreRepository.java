package ru.geracimov.otus.spring.hw18webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String>, GenreRepositoryCustom {

}
