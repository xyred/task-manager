package de.fherold.task_manager.controller;

import de.fherold.task_manager.dto.TaskListDto;
import de.fherold.task_manager.service.TaskListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasklists")
public class TaskListController {

    private final TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @PostMapping
    public ResponseEntity<TaskListDto> createTaskList(@RequestBody TaskListDto taskListDto) {
        var created = taskListService.createTaskList(taskListService.toEntity(taskListDto));
        return ResponseEntity.ok(taskListService.toDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskListDto> getTaskList(@PathVariable Long id) {
        return taskListService.getTaskList(id)
                .map(taskListService::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TaskListDto>> getAllTaskLists() {
        List<TaskListDto> lists = taskListService.getAllTaskLists().stream()
                .map(taskListService::toDto)
                .toList();
        return ResponseEntity.ok(lists);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskListDto> updateTaskList(@PathVariable Long id, @RequestBody TaskListDto taskListDto) {
        var updated = taskListService.updateTaskList(id, taskListService.toEntity(taskListDto));
        return updated != null ? ResponseEntity.ok(taskListService.toDto(updated)) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable Long id) {
        taskListService.deleteTaskList(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<TaskListDto>> getTaskListsByBoardId(@PathVariable Long boardId) {
        List<TaskListDto> taskLists = taskListService.getTaskListsByBoardId(boardId);
        return ResponseEntity.ok(taskLists);
    }
}
