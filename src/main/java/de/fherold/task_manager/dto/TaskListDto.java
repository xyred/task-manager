package de.fherold.task_manager.dto;

import java.util.List;

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
    private Long boardId; // ID of the associated board
    private List<Long> taskIds; // IDs of tasks in the task list
}
