package ru.geracimov.otus.spring.hw18webflux.exception;

public class AuthorNotExistsExeption extends RuntimeException {

    public AuthorNotExistsExeption() {
        super("Author Not Exists Exception");
    }

}
