package ru.geracimov.otus.spring.hw22springsecurityacl.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw22springsecurityacl.config.AclCreationService;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Book;
import ru.geracimov.otus.spring.hw22springsecurityacl.exception.NotFoundException;
import ru.geracimov.otus.spring.hw22springsecurityacl.repository.BookRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.AuthorService;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.BookService;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.GenreService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final AclCreationService aclCreationService;

    public Optional<Book> getBookById(Long uuid) {
        return bookRepository.findById(uuid);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAllBy();
    }

    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findBooksAndGenresByAuthors(authorService.getAuthorById(authorId)
                                                                       .orElseThrow(NotFoundException::new));
    }

    @Override
    public List<Book> getBooksByGenreId(Long genreId) {
        return bookRepository.findBooksAndGenresByGenres(genreService.getGenreById(genreId)
                                                                     .orElseThrow(NotFoundException::new));
    }

    public boolean delete(Long id) {
        try {
            bookRepository.deleteById(id);
            aclCreationService.dropAcl(new ObjectIdentityImpl(Book.class, id));
            return true;
        } catch (Exception var3) {
            log.error("Error adding book", var3);
            return false;
        }
    }

    public boolean delete(Book book) {
        return delete(book.getId());
    }

    public void save(Book book) {
        bookRepository.save(book);
        aclCreationService.addDefaultPrivilege(new ObjectIdentityImpl(book));
    }

}
