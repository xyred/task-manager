package de.fherold.task_manager.service;

import de.fherold.task_manager.dto.TaskListDto;
import de.fherold.task_manager.model.TaskList;
import de.fherold.task_manager.repository.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    public TaskList toEntity(TaskListDto dto) {
        if (dto == null)
            return null;
        return TaskList.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .position(dto.getPosition())
                .build();
    }

    public TaskListDto toDto(TaskList taskList) {
        if (taskList == null)
            return null;
        return TaskListDto.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .position(taskList.getPosition())
                .build();
    }

    public TaskList createTaskList(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public Optional<TaskList> getTaskList(Long id) {
        return taskListRepository.findById(id);
    }

    public List<TaskList> getAllTaskLists() {
        return taskListRepository.findAll();
    }

    public TaskList updateTaskList(Long id, TaskList updatedTaskList) {
        return taskListRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedTaskList.getTitle());
                    existing.setPosition(updatedTaskList.getPosition());
                    // Add more fields as needed
                    return taskListRepository.save(existing);
                })
                .orElse(null);
    }

    public void deleteTaskList(Long id) {
        taskListRepository.deleteById(id);
    }
}
