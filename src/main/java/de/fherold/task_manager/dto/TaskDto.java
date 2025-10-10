package de.fherold.task_manager.dto;

import java.util.Set;

import de.fherold.task_manager.model.Task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Task.
 * Used to transfer task data between layers.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskDto {
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Task List ID is required")
    private Long taskListId; // ID of the associated task list

    @NotNull(message = "Assignee IDs are required")
    private Set<Long> assigneeIds; // IDs of users assigned to the task
}
