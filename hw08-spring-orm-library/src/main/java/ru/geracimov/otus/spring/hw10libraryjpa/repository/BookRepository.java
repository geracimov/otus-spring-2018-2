package ru.geracimov.otus.spring.hw10libraryjpa.repository;

import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book> {

    Set<Book> getBooksByAuthor(Author author);

    List<Book> getAllBooks();

}
