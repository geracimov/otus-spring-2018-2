package ru.geracimov.otus.spring.hw12librarymongo.services;

import org.bson.types.ObjectId;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

    Author getById(ObjectId id);

    List<Author> getAll();

    boolean delete(ObjectId id);

    boolean delete(Author author);

    void save(Author author);

    Author save(String name,
                LocalDate birth);

}
