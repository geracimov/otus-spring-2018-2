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
    public List<Author> getAll() {
        return aRepo.findAll();
    }

    @Override
    public Author save(String name,
                       LocalDate birth) {
        return null;
    }

    @Override
    public boolean delete(ObjectId id) {
        return false;
    }

    @Override
    public boolean delete(Author author) {
        return false;
    }

    @Override
    public void save(Author author) {

    }
}
