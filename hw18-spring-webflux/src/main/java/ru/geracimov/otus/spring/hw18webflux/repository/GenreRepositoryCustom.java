package ru.geracimov.otus.spring.hw18webflux.repository;

public interface GenreRepositoryCustom {

    void removeGenreFromAllBooksByGenreId(String id);

}
