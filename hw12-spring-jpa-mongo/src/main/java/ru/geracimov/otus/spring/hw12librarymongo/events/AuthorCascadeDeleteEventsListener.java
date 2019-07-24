package ru.geracimov.otus.spring.hw12librarymongo.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw12librarymongo.domain.Author;
import ru.geracimov.otus.spring.hw12librarymongo.repository.AuthorRepository;

@Component
@RequiredArgsConstructor
public class AuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    private final AuthorRepository aRepo;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        ObjectId id = (ObjectId) source.get("_id");
        aRepo.removeAuthorsFromAllBooksByAuthorId(id);
    }

}
