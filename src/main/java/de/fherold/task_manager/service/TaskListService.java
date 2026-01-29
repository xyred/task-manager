package de.fherold.task_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.fherold.task_manager.dto.TaskListDto;
import de.fherold.task_manager.model.Board;
import de.fherold.task_manager.model.Task;
import de.fherold.task_manager.model.TaskList;
import de.fherold.task_manager.repository.BoardRepository;
import de.fherold.task_manager.repository.TaskListRepository;
import de.fherold.task_manager.repository.TaskRepository;

/**
 * Service class for managing task lists.
 * Provides methods to create and manipulate task lists.
 */

@Service
public class TaskListService {

    private final TaskListRepository taskListRepository;
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;

    public TaskListService(TaskListRepository taskListRepository, BoardRepository boardRepository,
            TaskRepository taskRepository) {
        this.taskListRepository = taskListRepository;
        this.boardRepository = boardRepository;
        this.taskRepository = taskRepository;
    }

    public TaskList toEntity(TaskListDto dto) {
        if (dto == null)
            return null;

        Board board = dto.getBoardId() == null ? null : boardRepository.findById(dto.getBoardId()).orElse(null);

        List<Task> tasks = dto.getTaskIds() == null ? List.of()
                : dto.getTaskIds().stream()
                        .map(taskRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList();

        return TaskList.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .position(dto.getPosition())
                .board(board)
                .tasks(tasks)
                .build();
    }

    public TaskListDto toDto(TaskList taskList) {
        if (taskList == null)
            return null;

        return TaskListDto.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .position(taskList.getPosition())
                .boardId(taskList.getBoard() == null ? null : taskList.getBoard().getId())
                .taskIds(taskList.getTasks() == null ? List.of()
                        : taskList.getTasks().stream()
                                .filter(t -> t != null && t.getId() != null)
                                .map(t -> t.getId())
                                .toList())
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

    public List<TaskListDto> getTaskListsByBoardId(Long boardId) {
        if (boardId == null) {
            throw new IllegalArgumentException("Board ID must not be null");
        }

        return taskListRepository.findByBoardId(boardId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskList updateTaskList(Long id, TaskList updatedTaskList) {
        return taskListRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedTaskList.getTitle());
                    existing.setPosition(updatedTaskList.getPosition());
                    existing.setBoard(updatedTaskList.getBoard());
                    existing.setTasks(updatedTaskList.getTasks());

                    return taskListRepository.save(existing);
                })
                .orElse(null);
    }

    public void deleteTaskList(Long id) {
        taskListRepository.deleteById(id);
    }
}
