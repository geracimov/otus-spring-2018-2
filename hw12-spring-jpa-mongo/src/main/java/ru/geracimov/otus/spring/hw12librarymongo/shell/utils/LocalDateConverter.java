package ru.geracimov.otus.spring.hw12librarymongo.shell.utils;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
class LocalDateConverter implements Converter<String, LocalDate> {

    @Override
    @Nullable
    public LocalDate convert(String name) {
        try {
            return LocalDate.parse(name);
        } catch (Exception e) {
            throw new IllegalArgumentException("Illegal date format - '" + name + "'");
        }

    }

}
