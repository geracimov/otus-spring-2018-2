package ru.geracimov.otus.spring.hw25springintegration.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

class MessageUtils {

    static Message emptyMessage() {
        return MessageBuilder.withPayload("")
                             .build();
    }


}
