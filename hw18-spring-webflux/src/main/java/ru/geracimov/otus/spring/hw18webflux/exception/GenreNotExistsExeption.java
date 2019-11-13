package ru.geracimov.otus.spring.hw18webflux.exception;

public class GenreNotExistsExeption extends RuntimeException {

    public GenreNotExistsExeption() {
        super("Genre Not Exists Exception");
    }

}
