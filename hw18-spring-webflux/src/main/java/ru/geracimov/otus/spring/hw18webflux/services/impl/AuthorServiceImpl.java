package ru.geracimov.otus.spring.hw18webflux.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;
import ru.geracimov.otus.spring.hw18webflux.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw18webflux.services.AuthorService;

import java.time.LocalDate;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Flux<Author> getAllAuthors() {
        Flux<Author> flux = authorRepository.findAllBy();
        return flux;
    }

    @Override
    public Flux<Author> getAllAuthorsByBirth(LocalDate from, LocalDate to) {
        return authorRepository.findAllByBirthBetween(from, to);
    }

    @Override
    public Mono<Author> getAuthorById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public Mono<Void> deleteAuthorById(String id) {
        return authorRepository.deleteById(id);
    }

    @Override
    public Mono<Author> save(Author author) {
        return authorRepository.save(author);
    }

}
