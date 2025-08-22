package de.fherold.task_manager.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TaskTest {
    
    @Test
    void builderShouldCreateTask() {
        Task task = Task.builder()
                .title("Test Task")
                .description("This is a test task")
                .status(Task.TaskStatus.TODO)
                .build();

        assertThat(task.getTitle()).isEqualTo("Test Task");
        assertThat(task.getDescription()).isEqualTo("This is a test task");
        assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.TODO);
        assertThat(task.getId()).isNull();
    }
}
