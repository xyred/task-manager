package de.fherold.task_manager.service;

import org.springframework.stereotype.Service;

import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.exception.TaskNotFoundException;
import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.repository.TaskRepository;

/**
 * Service class for managing tasks.
 * Provides methods to create and manipulate tasks.
 */

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto createTask(TaskDto dto) {
        Task task = toEntity(dto);
        Task saved = taskRepository.save(task);
        return toDto(saved);
    }

    private Task toEntity(TaskDto dto) {
        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    private TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toDto(task);
    }
}
