package ru.geracimov.otus.spring.hw18webflux.events;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw18webflux.domain.Genre;
import ru.geracimov.otus.spring.hw18webflux.repository.GenreRepository;

@Component
@RequiredArgsConstructor
public class GenreCascadeDeleteEventsListener extends AbstractMongoEventListener<Genre> {
    private final GenreRepository gRepo;

    @Override
    public void onAfterDelete(AfterDeleteEvent<Genre> event) {
        super.onAfterDelete(event);
        val source = event.getSource();
        String id = (String) source.get("_id");
        gRepo.removeGenreFromAllBooksByGenreId(id)
             .subscribe();
    }

}
