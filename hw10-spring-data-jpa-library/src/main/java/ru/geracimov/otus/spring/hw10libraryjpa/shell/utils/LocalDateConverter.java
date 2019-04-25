package ru.geracimov.otus.spring.hw10libraryjpa.shell.utils;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class LocalDateConverter implements Converter<String, LocalDate> {
    LocalDateConverter() {
    }

    public LocalDate convert(String name) {
        return LocalDate.parse(name);
    }
}
