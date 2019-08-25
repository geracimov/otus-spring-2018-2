package ru.geracimov.otus.spring.hw18webflux.exception;

public class BookNotExistsExeption extends RuntimeException {

    public BookNotExistsExeption() {
        super("Book Not Exists Execption");
    }

    public BookNotExistsExeption(String message) {
        super(message);
    }

}
