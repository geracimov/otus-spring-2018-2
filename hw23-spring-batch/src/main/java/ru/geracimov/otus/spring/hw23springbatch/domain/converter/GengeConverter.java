package ru.geracimov.otus.spring.hw23springbatch.domain.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.GenreMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.GenreH2;

@Component
public class GengeConverter implements Converter<GenreH2, GenreMongo> {

    @Override
    public GenreMongo convert(GenreH2 source) {
        return GenreMongo.builder()
                         .id(new ObjectId(UUIDConverter.asBytes(source.getId())))
                         .name(source.getName())
                         .build();
    }

}
