package de.fherold.task_manager.exception;

/**
 * Thrown when a user with the specified ID is not found.
 */

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User not found with id: " + id);
    }
}
