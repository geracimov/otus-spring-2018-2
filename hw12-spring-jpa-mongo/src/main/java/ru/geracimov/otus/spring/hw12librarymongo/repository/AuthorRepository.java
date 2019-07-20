package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, ObjectId> {

    @Query("{ 'name' : {$regex: ?0, $options: 'i'} }")
    List<Author> findByName(String name);

}
