package ru.geracimov.otus.spring.hw08libraryorm.shell.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
class UUIDConverter implements Converter<String, UUID> {

    @Override
    public UUID convert(String uuid) {
        return UUID.fromString(uuid);
    }
}
