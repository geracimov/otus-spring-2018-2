package ru.geracimov.otus.spring.hw23springbatch.domain.converter;

import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.geracimov.otus.spring.hw23springbatch.domain.nosql.AuthorMongo;
import ru.geracimov.otus.spring.hw23springbatch.domain.sql.AuthorH2;

@Component
public class AuthorConverter implements Converter<AuthorH2, AuthorMongo> {

    @Override
    public AuthorMongo convert(AuthorH2 source) {
        return AuthorMongo.builder()
                          .id(new ObjectId(UUIDConverter.asBytes(source.getId())))
                          .name(source.getName())
                          .birth(source.getBirth())
                          .build();
    }

}
