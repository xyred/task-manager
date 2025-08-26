package de.fherold.task_manager.exception;

/**
 * Thrown when a task with the specified ID is not found.
 */

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}
