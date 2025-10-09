package de.fherold.task_manager.repository;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import de.fherold.task_manager.model.TaskList;

@DataJpaTest
class TaskListRepositoryTest {

    @Autowired
    private TaskListRepository taskListRepository;

    @Test
    @DisplayName("Should save and find TaskList by id")
    void testSaveAndFindById() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Test List");
        taskList.setPosition(1);
        taskList.setBoard(null);
        
        TaskList saved = taskListRepository.save(taskList);

        Optional<TaskList> found = taskListRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Test List");
        assertThat(found.get().getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("Should return empty when TaskList not found")
    void testFindByIdNotFound() {
        Optional<TaskList> found = taskListRepository.findById(999L);
        assertThat(found).isEmpty();
    }

    @Test
    @DisplayName("Should delete TaskList")
    void testDeleteTaskList() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Delete List");
        TaskList saved = taskListRepository.save(taskList);

        taskListRepository.deleteById(saved.getId());
        Optional<TaskList> found = taskListRepository.findById(saved.getId());
        assertThat(found).isEmpty();
    }
}
