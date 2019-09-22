package ru.geracimov.otus.spring.hw23springbatch.domain.converter;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.AuthorMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.BookMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.GenreMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.AuthorH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.BookH2;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.GenreH2;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookConverter implements Converter<BookH2, BookMongo> {
    private final Converter<AuthorH2, AuthorMongo> authorConverter;
    private final Converter<GenreH2, GenreMongo> gengeConverter;

    @Override
    public BookMongo convert(BookH2 source) {
        return BookMongo.builder()
                        .id(new ObjectId(UUIDConverter.asBytes(source.getId())))
                        .isbn(source.getIsbn())
                        .name(source.getName())
                        .pageCount(source.getPageCount())
                        .year(source.getYear())
                        .authors(source.getAuthors()
                                       .stream()
                                       .map(authorConverter::convert)
                                       .collect(Collectors.toSet()))
                        .genres(source.getGenres()
                                      .stream()
                                      .map(gengeConverter::convert)
                                      .collect(Collectors.toSet()))
                        .build();
    }

}
