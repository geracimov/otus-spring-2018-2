package ru.geracimov.otus.spring.hw08libraryorm.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Author;
import ru.geracimov.otus.spring.hw08libraryorm.domain.Book;
import ru.geracimov.otus.spring.hw08libraryorm.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw08libraryorm.services.AuthorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Author getAuthorById(UUID uuid) {
        return authorRepository.read(Author.class, uuid);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.getAllAuthors();
    }

    @Override
    public Set<Book> getBooksByAuthor(Author author) {
        return authorRepository.getBooksByAuthor(author);
    }

    @Override
    public Set<Book> getBooksByAuthor(UUID uuid) {
        return authorRepository.getBooksByAuthor(uuid);
    }

    @Override
    public Author addAuthor(String name,
                            LocalDate birth) {
        try {
            Author author = new Author(name, birth);
            authorRepository.create(author);
            return author;
        } catch (Exception e) {
            log.error("Error adding author", e);
        }
        return null;
    }

    @Override
    public boolean delete(UUID id) {
        Author author = authorRepository.read(Author.class, id);
        return delete(author);
    }

    @Override
    public boolean delete(Author author) {
        try {
            authorRepository.delete(author);
        } catch (Exception e) {
            log.error("Error deleting author - " + author.getId(), e);
            return false;
        }
        return true;
    }

}
