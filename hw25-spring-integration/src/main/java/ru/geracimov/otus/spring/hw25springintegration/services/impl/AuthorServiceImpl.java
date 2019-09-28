package ru.geracimov.otus.spring.hw25springintegration.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw25springintegration.config.AclCreationService;
import ru.geracimov.otus.spring.hw25springintegration.domain.Author;
import ru.geracimov.otus.spring.hw25springintegration.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw25springintegration.services.AuthorService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AclCreationService aclCreationService;

    public Optional<Author> getAuthorById(Long uuid) {
        return Optional.of(authorRepository.getOne(uuid));
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public boolean delete(Long id) {
        try {
            authorRepository.deleteById(id);
            aclCreationService.dropAcl(new ObjectIdentityImpl(Author.class, id));
            return true;
        } catch (Exception var3) {
            log.error("Error deleting author - " + id, var3);
            return false;
        }
    }

    public boolean delete(Author author) {
        return delete(author.getId());
    }

    @Transactional
    public void save(Author author) {
        Author saved = authorRepository.saveAndFlush(author);
        aclCreationService.addDefaultPrivilege(new ObjectIdentityImpl(saved));
    }
}
