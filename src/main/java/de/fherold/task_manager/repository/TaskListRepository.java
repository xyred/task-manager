package de.fherold.task_manager.repository;

import de.fherold.task_manager.model.TaskList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

}
