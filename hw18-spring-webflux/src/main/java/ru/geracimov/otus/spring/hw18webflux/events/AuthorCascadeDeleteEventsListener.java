package ru.geracimov.otus.spring.hw18webflux.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.geracimov.otus.spring.hw18webflux.domain.Author;
import ru.geracimov.otus.spring.hw18webflux.repository.AuthorRepository;
import ru.geracimov.otus.spring.hw18webflux.repository.BookRepository;

@Component
@RequiredArgsConstructor
public class AuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    private final BookRepository bRepo;
    private final AuthorRepository aRepo;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);

        val mAuthor = Mono.just(event.getSource())
                          .map(s -> s.get("_id")
                                     .toString())
                          .flatMap(aRepo::findAuthorById);


        bRepo.findAllByAuthorsContaining(mAuthor)
             .doOnNext(b -> b.getAuthors()
                             .remove(mAuthor))
             .doOnNext(bRepo::save)
             .subscribe();
    }

}
