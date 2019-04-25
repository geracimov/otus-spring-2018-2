package ru.geracimov.otus.spring.hw10libraryjpa.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw10libraryjpa.domain.Author;
import ru.geracimov.otus.spring.hw10libraryjpa.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw10libraryjpa.services.AuthorService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static final Logger log = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(final AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthorById(UUID uuid) {
        return this.authorRepository.getOne(uuid);
    }

    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    public Author addAuthor(String name, LocalDate birth) {
        try {
            Author author = new Author(name, birth);
            this.authorRepository.save(author);
            return author;
        } catch (Exception var4) {
            log.error("Error adding author", var4);
            return null;
        }
    }

    public boolean delete(UUID id) {
        try {
            this.authorRepository.deleteById(id);
            return true;
        } catch (Exception var3) {
            log.error("Error deleting author - " + id, var3);
            return false;
        }
    }

    public boolean delete(Author author) {
        return this.delete(author.getId());
    }

    public void save(Author author) {
        this.authorRepository.saveAndFlush(author);
    }
}
