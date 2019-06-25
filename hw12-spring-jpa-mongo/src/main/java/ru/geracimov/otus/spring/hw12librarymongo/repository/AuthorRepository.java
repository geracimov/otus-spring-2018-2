package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;

import java.util.UUID;

public interface AuthorRepository extends MongoRepository<Author, UUID> {
}
