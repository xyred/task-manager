package de.fherold.task_manager.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.service.TaskService;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockitoBean
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

        @Test
        void getTask_shouldReturnTaskWhenFound() throws Exception {

                Long id = 1L;
                TaskDto dto = TaskDto.builder()
                                .id(id)
                                .title("Test Task")
                                .description("Test Description")
                                .status(Task.TaskStatus.TODO)
                                .build();

                when(taskService.getTask(id)).thenReturn(dto);

                mockMvc.perform(get("/tasks/{id}", id))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(id))
                                .andExpect(jsonPath("$.title").value("Test Task"))
                                .andExpect(jsonPath("$.description").value("Test Description"))
                                .andExpect(jsonPath("$.status").value("TODO"));
        }

        @Test
        void getAllTasks_shouldReturnListOfTasks() throws Exception {

                List<TaskDto> tasks = List.of(
                                TaskDto.builder().id(1L)
                                                .title("Task 1")
                                                .description("Desc 1")
                                                .status(Task.TaskStatus.TODO).build(),
                                TaskDto.builder().id(2L)
                                                .title("Task 2")
                                                .description("Desc 2")
                                                .status(Task.TaskStatus.DONE).build());

                when(taskService.getAllTasks()).thenReturn(tasks);

                mockMvc.perform(get("/tasks"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.length()").value(2))
                                .andExpect(jsonPath("$[0].id").value(1L))
                                .andExpect(jsonPath("$[0].title").value("Task 1"))
                                .andExpect(jsonPath("$[0].description").value("Desc 1"))
                                .andExpect(jsonPath("$[0].status").value("TODO"))
                                .andExpect(jsonPath("$[1].id").value(2L))
                                .andExpect(jsonPath("$[1].title").value("Task 2"))
                                .andExpect(jsonPath("$[1].description").value("Desc 2"))
                                .andExpect(jsonPath("$[1].status").value("DONE"));
        }

        @Test
        void updateTask_shouldReturnUpdatedTask() throws Exception {
                Long id = 1L;

                TaskDto inputDto = TaskDto.builder()
                                .title("Updated Task")
                                .description("Updated Description")
                                .status(Task.TaskStatus.DONE)
                                .build();

                TaskDto updatedDto = TaskDto.builder()
                                .id(id)
                                .title("Updated Task")
                                .description("Updated Description")
                                .status(Task.TaskStatus.DONE)
                                .build();

                when(taskService.updateTask(id, inputDto)).thenReturn(updatedDto);

                String json = """
                                {
                                    "title": "Updated Task",
                                    "description": "Updated Description",
                                    "status": "DONE"
                                }
                                """;

                mockMvc.perform(put("/tasks/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value(id))
                                .andExpect(jsonPath("$.title").value("Updated Task"))
                                .andExpect(jsonPath("$.description").value("Updated Description"))
                                .andExpect(jsonPath("$.status").value("DONE"));
        }
}
