package de.fherold.task_manager.service;

import de.fherold.task_manager.dto.TaskDto;
import de.fherold.task_manager.exception.TaskNotFoundException;
import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.repository.TaskRepository;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public Task toEntity(TaskDto dto) {
        if (dto == null)
            return null;
        return Task.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    public TaskDto toDto(Task task) {
        if (task == null)
            return null;
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    public TaskDto createTask(TaskDto dto) {
        Task task = toEntity(dto);
        Task saved = taskRepository.save(task);
        return toDto(saved);
    }

    public TaskDto getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toDto(task);
    }

    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream().map(this::toDto).toList();
    }

    public TaskDto updateTask(Long id, TaskDto dto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());

        Task updated = taskRepository.save(task);
        return toDto(updated);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }
}
