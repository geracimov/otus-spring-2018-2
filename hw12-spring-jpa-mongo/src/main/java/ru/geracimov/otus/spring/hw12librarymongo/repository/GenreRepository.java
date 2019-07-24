package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, ObjectId>, GenreRepositoryCustom {

}
