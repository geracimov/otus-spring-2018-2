package ru.geracimov.otus.spring.hw18webflux.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;
import ru.geracimov.otus.spring.hw18webflux.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class AuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    private final BookRepository bRepo;

    @Override
    public void onAfterDelete(@NonNull AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);

        String authorId = event.getSource()
                               .get("id")
                               .toString();

        bRepo.removeAuthorFromBookById(authorId)
             .subscribe();
    }

}
