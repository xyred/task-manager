package de.fherold.task_manager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Task List.
 * Used to transfer task list data between layers.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskListDto {
    private Long id;
    private String title;
    private Integer position;
}
