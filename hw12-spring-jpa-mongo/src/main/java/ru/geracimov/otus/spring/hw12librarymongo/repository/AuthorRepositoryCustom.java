package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;

import java.util.List;


public interface AuthorRepositoryCustom {

    void removeAuthorsFromAllBooksByAuthorId(ObjectId id);

    List<Author> getAuthorsByBookId(ObjectId bookId);

}
