package de.fherold.task_manager.repository;

import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.model.Task.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void testSaveAndFindTask() {
        Task task = Task.builder()
                .title("Sample Task")
                .description("This is a sample task.")
                .status(TaskStatus.TODO)
                .build();

        Task saved = taskRepository.save(task);
        assertThat(saved.getId()).isNotNull();

        Task found = taskRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getTitle()).isEqualTo("Sample Task");
        assertThat(found.getDescription()).isEqualTo("This is a sample task.");
        assertThat(found.getStatus()).isEqualTo(TaskStatus.TODO);
    }
}