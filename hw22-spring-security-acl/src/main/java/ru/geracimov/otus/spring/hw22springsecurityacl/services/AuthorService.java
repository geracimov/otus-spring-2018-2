package ru.geracimov.otus.spring.hw22springsecurityacl.services;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    @PostAuthorize("hasPermission(returnObject.get().id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author', 'READ')")
    Optional<Author> getAuthorById(Long id);

    @PostFilter(value = "filterObject.name.matches('Л.*') or hasAuthority('ADMIN') or hasPermission(filterObject, 'READ')")
    List<Author> getAllAuthors();

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN') " +
            "or hasPermission(#id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author', 'DELETE')")
    boolean delete(Long id);

    @PreAuthorize("hasAnyAuthority('EDITOR','ADMIN') " +
            "or hasPermission(#author.id, 'ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author', 'DELETE')")
    boolean delete(Author author);

    void save(Author author);

}
