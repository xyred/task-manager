package de.fherold.task_manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.fherold.task_manager.model.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

    // Find all task lists associated with a specific board ID
    List<TaskList> findByBoardId(Long boardId);
}
