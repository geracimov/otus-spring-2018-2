package ru.geracimov.otus.spring.hw10libraryjpa.shell.utils;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomPromptProvider implements PromptProvider {
    public CustomPromptProvider() {
    }

    public AttributedString getPrompt() {
        return new AttributedString(":>", AttributedStyle.DEFAULT.foreground(4));
    }
}
