package de.fherold.task_manager.repository;

import de.fherold.task_manager.model.TaskList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskListRepositoryTest {

    @Autowired
    private TaskListRepository taskListRepository;

    @Test
    void testSaveAndFindTaskList() {
        TaskList list = TaskList.builder()
                .name("To Do")
                .position(1)
                .build();

        TaskList saved = taskListRepository.save(list);
        assertThat(saved.getId()).isNotNull();

        TaskList found = taskListRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("To Do");
        assertThat(found.getPosition()).isEqualTo(1);
    }
}