package de.fherold.task_manager.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.service.TaskService;

public class TaskControllerTest {

    private final TaskService taskService = Mockito.mock(TaskService.class);
    private final TaskController taskController = new TaskController(taskService);

    @Test
    void testCreateTask() {
        TaskDto dto = TaskDto.builder().title("New Task").description("Desc").status(Task.TaskStatus.TODO).build();
        TaskDto saved = TaskDto.builder().id(1L).title("New Task").description("Desc").status(Task.TaskStatus.TODO)
                .build();
        Mockito.when(taskService.createTask(dto)).thenReturn(saved);

        ResponseEntity<TaskDto> response = taskController.createTask(dto);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(saved);
    }

    @Test
    void testDeleteTask() {
        ResponseEntity<Void> response = taskController.deleteTask(1L);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void testGetAllTasks() {
        TaskDto dto1 = TaskDto.builder().id(1L).title("Task1").build();
        TaskDto dto2 = TaskDto.builder().id(2L).title("Task2").build();
        Mockito.when(taskService.getAllTasks()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<List<TaskDto>> response = taskController.getAllTasks();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsExactly(dto1, dto2);
    }

    @Test
    void testGetTask() {
        TaskDto dto = TaskDto.builder().id(1L).title("Task1").build();
        Mockito.when(taskService.getTask(1L)).thenReturn(dto);

        ResponseEntity<TaskDto> response = taskController.getTask(1L);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void testUpdateTask() {
        TaskDto dto = TaskDto.builder().title("Updated").build();
        TaskDto updated = TaskDto.builder().id(1L).title("Updated").build();
        Mockito.when(taskService.updateTask(1L, dto)).thenReturn(updated);

        ResponseEntity<TaskDto> response = taskController.updateTask(1L, dto);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(updated);
    }
}