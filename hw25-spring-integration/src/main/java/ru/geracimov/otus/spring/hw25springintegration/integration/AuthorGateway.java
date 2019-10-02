package ru.geracimov.otus.spring.hw25springintegration.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.geracimov.otus.spring.hw25springintegration.domain.Author;

import java.util.List;
import java.util.Optional;

@MessagingGateway
public interface AuthorGateway {

    @Gateway(requestChannel = "allAuthorsChannelInput", replyChannel = "allAuthorsChannelOutput")
    List<Author> getAll(Message o);

    @Gateway(requestChannel = "simpleAuthorChannelInput", replyChannel = "simpleAuthorChannelOutput")
    Optional<Author> getAuthorById(Long id);

    @Gateway(requestChannel = "deleteAuthorChannelInput", replyChannel = "deleteAuthorChannelOutput")
    boolean delete(Author author);

    @Gateway(requestChannel = "addAuthorChannelInput", replyChannel = "addAuthorChannelOutput")
    void save(Author person);

}
