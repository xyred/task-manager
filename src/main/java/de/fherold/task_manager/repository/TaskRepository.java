package de.fherold.task_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.fherold.task_manager.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find all tasks associated with a specific task list ID
    List<Task> findByTaskListId(Long taskListId);
}
