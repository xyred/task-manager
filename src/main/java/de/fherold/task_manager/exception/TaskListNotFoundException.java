package de.fherold.task_manager.exception;

/**
 * Thrown when a task list with the specified ID is not found.
 */

public class TaskListNotFoundException extends RuntimeException {
    public TaskListNotFoundException(Long id) {
        super("TaskList not found with id: " + id);
    }
}
