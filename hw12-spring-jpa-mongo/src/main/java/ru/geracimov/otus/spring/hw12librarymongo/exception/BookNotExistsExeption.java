package ru.geracimov.otus.spring.hw12librarymongo.exception;

public class BookNotExistsExeption extends RuntimeException {

    public BookNotExistsExeption() {
        super("Book Not Exists Execption");
    }

    public BookNotExistsExeption(String message) {
        super(message);
    }

}
