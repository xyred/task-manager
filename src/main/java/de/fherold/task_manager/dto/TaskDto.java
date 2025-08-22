package de.fherold.task_manager.dto;

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
}
