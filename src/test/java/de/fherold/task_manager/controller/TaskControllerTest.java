package de.fherold.task_manager.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.service.TaskService;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    @Test
    void createTask_shouldReturnCreatedTask() throws Exception {

        TaskDto inputDto = TaskDto.builder()
                .title("Test Task")
                .description("Test Description")
                .status(Task.TaskStatus.TODO)
                .build();

        TaskDto savedDto = TaskDto.builder()
                .id(1L)
                .title("Test Task")
                .description("Test Description")
                .status(Task.TaskStatus.TODO)
                .build();

        when(taskService.createTask(inputDto)).thenReturn(savedDto);

        String json = """
            {
                "title": "Test Task",
                "description": "Test Description",
                "status": "TODO"
            }
            """;

            mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("Test Task"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.status").value("TODO"));
    }
}
