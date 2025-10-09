package de.fherold.task_manager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.exception.TaskNotFoundException;
import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.model.Task.TaskStatus;
import de.fherold.task_manager.model.TaskList;
import de.fherold.task_manager.model.User;
import de.fherold.task_manager.repository.TaskListRepository;
import de.fherold.task_manager.repository.TaskRepository;
import de.fherold.task_manager.repository.UserRepository;

public class TaskServiceTest {

    private TaskRepository taskRepository;
    private TaskListRepository taskListRepository;
    private UserRepository userRepository;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskRepository = mock(TaskRepository.class);
        taskListRepository = mock(TaskListRepository.class);
        userRepository = mock(UserRepository.class);
        taskService = new TaskService(taskRepository, taskListRepository, userRepository);
    }

    @Test
    void toEntity_shouldConvertDtoToEntity() {
        Long taskListId = 2L;
        Long userId = 3L;
        TaskList taskList = TaskList.builder().id(taskListId).build();
        User user = User.builder().id(userId).build();

        TaskDto dto = TaskDto.builder()
                .id(1L)
                .title("Title")
                .description("Desc")
                .status(TaskStatus.TODO)
                .taskListId(taskListId)
                .assigneeIds(Set.of(userId))
                .build();

        when(taskListRepository.findById(taskListId)).thenReturn(Optional.of(taskList));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Task entity = taskService.toEntity(dto);

        assertThat(entity.getId()).isEqualTo(1L);
        assertThat(entity.getTitle()).isEqualTo("Title");
        assertThat(entity.getDescription()).isEqualTo("Desc");
        assertThat(entity.getStatus()).isEqualTo(TaskStatus.TODO);
        assertThat(entity.getTaskList()).isEqualTo(taskList);
        assertThat(entity.getAssignees()).containsExactly(user);
    }

    @Test
    void toDto_shouldConvertEntityToDto() {
        Long taskListId = 2L;
        Long userId = 3L;
        TaskList taskList = TaskList.builder().id(taskListId).build();
        User user = User.builder().id(userId).build();

        Task task = Task.builder()
                .id(1L)
                .title("Title")
                .description("Desc")
                .status(TaskStatus.TODO)
                .taskList(taskList)
                .assignees(Set.of(user))
                .build();

        TaskDto dto = taskService.toDto(task);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("Title");
        assertThat(dto.getDescription()).isEqualTo("Desc");
        assertThat(dto.getStatus()).isEqualTo(TaskStatus.TODO);
        assertThat(dto.getTaskListId()).isEqualTo(taskListId);
        assertThat(dto.getAssigneeIds()).containsExactly(userId);
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

    @Test
    void getTask_shouldReturnTaskDto() {
        Task task = Task.builder()
                .id(1L)
                .title("Task")
                .description("Desc")
                .status(TaskStatus.TODO)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDto dto = taskService.getTask(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getTitle()).isEqualTo("Task");
    }

    @Test
    void getTask_shouldThrowIfNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.getTask(1L));
    }

    @Test
    void getAllTasks_shouldReturnListOfTaskDtos() {
        Task task1 = Task.builder().id(1L).title("A").build();
        Task task2 = Task.builder().id(2L).title("B").build();

        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        List<TaskDto> dtos = taskService.getAllTasks();

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(0).getId()).isEqualTo(1L);
        assertThat(dtos.get(1).getId()).isEqualTo(2L);
    }

    @Test
    void updateTask_shouldUpdateAndReturnTaskDto() {
        Task task = Task.builder()
                .id(1L)
                .title("Old Title")
                .description("Old Desc")
                .status(TaskStatus.TODO)
                .build();

        TaskDto updateDto = TaskDto.builder()
                .title("New Title")
                .description("New Desc")
                .status(TaskStatus.IN_PROGRESS)
                .build();

        Task updatedTask = Task.builder()
                .id(1L)
                .title("New Title")
                .description("New Desc")
                .status(TaskStatus.IN_PROGRESS)
                .build();

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        TaskDto result = taskService.updateTask(1L, updateDto);

        assertThat(result.getTitle()).isEqualTo("New Title");
        assertThat(result.getDescription()).isEqualTo("New Desc");
        assertThat(result.getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }

    @Test
    void updateTask_shouldThrowIfNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        TaskDto dto = TaskDto.builder().title("X").build();
        assertThrows(TaskNotFoundException.class, () -> taskService.updateTask(1L, dto));
    }

    @Test
    void deleteTask_shouldDeleteIfExists() {
        Task task = Task.builder().id(1L).build();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).delete(task);

        taskService.deleteTask(1L);

        verify(taskRepository).delete(task);
    }

    @Test
    void deleteTask_shouldThrowIfNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(1L));
    }
}
