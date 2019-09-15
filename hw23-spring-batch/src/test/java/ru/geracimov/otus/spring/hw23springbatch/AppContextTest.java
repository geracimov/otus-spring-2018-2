package ru.geracimov.otus.spring.hw23springbatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.shell.jline.InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED;
import static org.springframework.shell.jline.ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED;

@SpringBootTest(properties = {SPRING_SHELL_INTERACTIVE_ENABLED + "=false", SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@DisplayName("Контекст приложения")
public class AppContextTest {

    @Test
    @DisplayName("создается корректно")
    public void contextIsCorrectlyLoaded() {

    }

}
