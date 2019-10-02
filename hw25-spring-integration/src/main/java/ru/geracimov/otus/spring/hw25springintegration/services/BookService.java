package ru.geracimov.otus.spring.hw25springintegration.services;


import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.geracimov.otus.spring.hw25springintegration.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getBookById(Long uuid);

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN') or hasPermission(#id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book','DELETE')")
    boolean delete(Long id);

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN') or hasPermission(#book.id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book','DELETE')")
    boolean delete(Book book);

    void save(Book book);

    @PostFilter("hasAuthority('USER') && filterObject.name.matches('.*[АаAa].*')")
    List<Book> getAllBooks();

    List<Book> getBooksByAuthorId(Long authorId);

    List<Book> getBooksByGenreId(Long genreId);

}
