package ru.geracimov.otus.spring.hw23springbatch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.shell.jline.InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED;
import static org.springframework.shell.jline.ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED;

@SpringBootTest(properties = {SPRING_SHELL_INTERACTIVE_ENABLED + "=false", SPRING_SHELL_SCRIPT_ENABLED + "=false"})
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Запуск задачи миграции")
public class SpringBathMigrationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    @DisplayName("успешно завершается")
    public void testJobRestart() throws Exception {
        JobExecution jobExecution1 = jobLauncherTestUtils.launchJob();
        assertThat(jobExecution1.getJobInstance().getJobName()).isEqualTo("migrateBooksJob");
        assertThat(jobExecution1.getStatus()).isEqualTo(BatchStatus.COMPLETED);
        assertThat(jobExecution1.getStepExecutions().size()).isEqualTo(3);
        assertThat(jobExecution1.getFailureExceptions().size()).isEqualTo(0);

    }
}
