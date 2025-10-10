package de.fherold.task_manager.exception;

/**
 * Thrown when a board with the specified ID is not found.
 */

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(Long id) {
        super("Board not found with id: " + id);
    }
}
