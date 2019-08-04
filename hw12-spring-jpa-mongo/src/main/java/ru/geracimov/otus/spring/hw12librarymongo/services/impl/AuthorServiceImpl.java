package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.exception.AuthorNotExistsExeption;
import ru.geracimov.otus.spring.hw12librarymongo.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw12librarymongo.services.AuthorService;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository aRepo;

    @Override
    public Author getById(ObjectId id) {
        return aRepo.findById(id)
                    .orElseThrow(AuthorNotExistsExeption::new);
    }

    @Override
    public List<Author> getByName(String name) {
        return aRepo.findByName(name);
    }

    @Override
    public List<Author> getByBook(ObjectId bookId) {
        return aRepo.getAuthorsByBookId(bookId);
    }

    @Override
    public List<Author> getByBirthBetween(LocalDate from,
                                          LocalDate to) {
        return aRepo.findByBirthBetween(from, to);
    }

    @Override
    public List<Author> getAll() {
        return aRepo.findAll();
    }

    @Override
    public void delete(ObjectId id) {
        aRepo.deleteById(id);
    }

    @Override
    public Author save(Author author) {
        return aRepo.save(author);
    }
}
