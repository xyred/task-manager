package de.fherold.task_manager.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for User.
 * Used to transfer user data between layers.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Set<Long> boardIds; // IDs of boards the user is a member of
    private Set<Long> assignedTaskIds; // IDs of tasks assigned to the user
}
