package de.fherold.task_manager.dto;

import java.util.Set;

import de.fherold.task_manager.model.Task.TaskStatus;
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
    private String title;
    private String description;
    private TaskStatus status;
    private Long taskListId; // ID of the associated task list
    private Set<Long> assigneeIds; // IDs of users assigned to the task
}
