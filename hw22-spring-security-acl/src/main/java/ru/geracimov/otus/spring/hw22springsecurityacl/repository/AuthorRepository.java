package ru.geracimov.otus.spring.hw22springsecurityacl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geracimov.otus.spring.hw22springsecurityacl.domain.Author;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
