package ru.geracimov.otus.spring.hw22springsecurityacl.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geracimov.otus.spring.hw22springsecurityacl.config.AclCreationService;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Genre;
import ru.geracimov.otus.spring.hw22springsecurityacl.repository.GenreRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.services.GenreService;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final AclCreationService aclCreationService;

    public Optional<Genre> getGenreById(Long uuid) {
        return Optional.of(genreRepository.getOne(uuid));
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public boolean delete(Long id) {
        try {
            genreRepository.deleteById(id);
            aclCreationService.dropAcl(new ObjectIdentityImpl(Genre.class, id));
            return true;
        } catch (Exception var3) {
            log.error("Error deleting genre - " + id, var3);
            return false;
        }
    }

    public boolean delete(Genre genre) {
        return delete(genre.getId());
    }

    public void save(Genre genre) {
        genreRepository.saveAndFlush(genre);
        aclCreationService.addDefaultPrivilege(new ObjectIdentityImpl(genre));
    }
}
