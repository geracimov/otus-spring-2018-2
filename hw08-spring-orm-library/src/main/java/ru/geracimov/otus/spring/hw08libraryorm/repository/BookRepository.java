package ru.geracimov.otus.spring.hw08libraryorm.repository;

import ru.geracimov.otus.spring.hw08libraryorm.domain.Author;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository extends CrudRepository<Book> {

    Set<Book> getBooksByAuthor(Author author);

    List<Book> getAllBooks();

}
