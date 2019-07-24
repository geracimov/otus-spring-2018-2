package ru.geracimov.otus.spring.hw12librarymongo.services.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Genre;
import ru.geracimov.otus.spring.hw12librarymongo.exception.GenreNotExistsExeption;
import ru.geracimov.otus.spring.hw12librarymongo.repository.GenreRepository;
import ru.geracimov.otus.spring.hw12librarymongo.services.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository gRepo;

    @Override
    public Genre getGenreById(ObjectId id) {
        return gRepo.findById(id)
                    .orElseThrow(GenreNotExistsExeption::new);
    }

    @Override
    public Page<Genre> getAllGenres(Pageable pageable) {
        return gRepo.findAll(pageable);
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return gRepo.save(genre);
    }

    @Override
    public Genre saveGenre(String name) {
        var genre = new Genre(name);
        return saveGenre(genre);
    }

    @Override
    public void deleteGenre(ObjectId id) {
        gRepo.deleteById(id);
    }

}
