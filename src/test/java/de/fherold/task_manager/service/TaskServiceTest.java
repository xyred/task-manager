package de.fherold.task_manager.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.model.Task.TaskStatus;
import de.fherold.task_manager.repository.TaskRepository;

public class TaskServiceTest {
    private TaskRepository taskRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository);
    }

    @Test
    void createTask_shouldSaveAndReturnTaskDto() {
        TaskDto inputDto = TaskDto.builder()
                .title("Test Task")
                .description("Test Description")
                .status(TaskStatus.TODO)
                .build();

        Task savedTask = Task.builder()
                .id(1L)
                .title("Test Task")
                .description("Test Description")
                .status(TaskStatus.TODO)
                .build();

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);
        
        TaskDto result = taskService.createTask(inputDto);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Test Task");
        assertThat(result.getDescription()).isEqualTo("Test Description");
        assertThat(result.getStatus()).isEqualTo(TaskStatus.TODO);
    }
}