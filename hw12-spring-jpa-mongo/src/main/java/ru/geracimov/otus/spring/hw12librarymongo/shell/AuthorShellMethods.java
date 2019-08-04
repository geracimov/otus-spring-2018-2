package ru.geracimov.otus.spring.hw12librarymongo.shell;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.services.AuthorService;

import java.time.LocalDate;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AuthorShellMethods {
    private final AuthorService authorService;

    @ShellMethod(value = "Add new author by <name> and <birth>", group = "authors", key = {"author-add"})
    public String addNewAuthor(@ShellOption String name, @ShellOption LocalDate birth) {
        var author = new Author(name, birth);
        Author authorSaved = this.authorService.save(author);
        return "Added " + authorSaved.getId();
    }

    @ShellMethod(value = "Delete author by <id>", group = "authors", key = {"author-del"})
    public String delAuthor(@ShellOption ObjectId id) {
        this.authorService.delete(id);
        return "Deleted " + id;
    }

    @ShellMethod(value = "Print all authors", group = "authors", key = {"authors"})
    public void printAllAuthors() {
        List<Author> authors = this.authorService.getAll();
        authors.forEach(System.out::println);
        System.out.println("Total: " + authors.size());
    }

    @ShellMethod(value = "Print one author by <id>", group = "authors", key = {"authors-find-byId"})
    public void printAuthorById(ObjectId id) {
        System.out.println("Search result:");
        System.out.println(authorService.getById(id));
    }

    @ShellMethod(value = "Print authors by <book>", group = "authors", key = {"authors-find-byBook"})
    public void printAuthorByBook(ObjectId id) {
        System.out.println("Search result:");
        var authors = authorService.getByBook(id);
        authors.forEach(System.out::println);
        System.out.println("Total: " + authors.size());
    }

    @ShellMethod(value = "Print authors by <name>", group = "authors", key = {"authors-find-byName"})
    public void printAuthorByName(String name) {
        System.out.println("Search result:");
        var authors = authorService.getByName(name);
        authors.forEach(System.out::println);
        System.out.println("Total: " + authors.size());
    }

    @ShellMethod(value = "Print authors birth between <from> and <to>", group = "authors", key = {"authors-find-byBirthBetween"})
    public void printAuthorByBirth(LocalDate from, LocalDate to) {
        System.out.println("Search result:");
        var authors = authorService.getByBirthBetween(from, to);
        authors.forEach(System.out::println);
        System.out.println("Total: " + authors.size());
    }

}
