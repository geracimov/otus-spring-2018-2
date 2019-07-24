package ru.geracimov.otus.spring.hw12librarymongo.exception;

public class GenreNotExistsExeption extends RuntimeException {

    public GenreNotExistsExeption() {
        super("Genre Not Exists Exception");
    }

}
