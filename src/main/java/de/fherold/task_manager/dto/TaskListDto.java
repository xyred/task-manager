package de.fherold.task_manager.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @NotNull(message = "Position is required")
    private Integer position; // Position of the task list in the board

    @NotNull(message = "Board ID is required")
    private Long boardId; // ID of the associated board

    private List<Long> taskIds; // IDs of tasks in the task list
}
