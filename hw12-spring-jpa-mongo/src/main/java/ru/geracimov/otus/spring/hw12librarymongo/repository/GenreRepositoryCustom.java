package ru.geracimov.otus.spring.hw12librarymongo.repository;

import org.bson.types.ObjectId;


public interface GenreRepositoryCustom {

    void removeGenreFromAllBooksByGenreId(ObjectId id);

}
