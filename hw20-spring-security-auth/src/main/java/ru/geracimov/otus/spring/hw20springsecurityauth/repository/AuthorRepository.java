package ru.geracimov.otus.spring.hw20springsecurityauth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw20springsecurityauth.domain.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
