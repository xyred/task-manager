package de.fherold.task_manager.dto;

import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Board.
 * Used to transfer board data between layers.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private Long id;
    private String title;
    private String description;
    private List<Long> userId; // ID of the user associated with the board
    private Set<Long> memberIds; // IDs of users who are members of the board
}
