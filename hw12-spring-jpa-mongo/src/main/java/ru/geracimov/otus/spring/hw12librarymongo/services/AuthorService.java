package ru.geracimov.otus.spring.hw12librarymongo.services;

import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

    List<Author> getByName(String name);

    List<Author> getByBirthBetween(LocalDate from, LocalDate to);

    Author getById(ObjectId id);

    List<Author> getByBook(ObjectId bookId);

    List<Author> getAll();

    void delete(ObjectId id);

    Author save(Author author);

}
