package de.fherold.task_manager.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void testBuilder() {
        TaskList mockList = TaskList.builder().id(1L).title("List").position(1).build();
        User user1 = User.builder().id(1L).username("user1").email("user1@example.com").build();
        User user2 = User.builder().id(2L).username("user2").email("user2@example.com").build();

        Task task = Task.builder()
                .title("Test Task")
                .description("This is a test task")
                .status(Task.TaskStatus.TODO)
                .taskList(mockList)
                .assignees(Set.of(user1, user2))
                .build();

        assertThat(task.getTitle()).isEqualTo("Test Task");
        assertThat(task.getDescription()).isEqualTo("This is a test task");
        assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.TODO);
        assertThat(task.getTaskList()).isEqualTo(mockList);
        assertThat(task.getAssignees()).containsExactlyInAnyOrder(user1, user2);
        assertThat(task.getId()).isNull();
    }

    @Test
    void testGetAssignees() {
        User user = User.builder().id(1L).username("user").email("user@example.com").build();
        Task task = new Task();
        task.setAssignees(Set.of(user));
        assertThat(task.getAssignees()).containsExactly(user);
    }

    @Test
    void testGetDescription() {
        Task task = new Task();
        task.setDescription("desc");
        assertThat(task.getDescription()).isEqualTo("desc");
    }

    @Test
    void testGetId() {
        Task task = new Task();
        task.setId(42L);
        assertThat(task.getId()).isEqualTo(42L);
    }

    @Test
    void testGetStatus() {
        Task task = new Task();
        task.setStatus(Task.TaskStatus.IN_PROGRESS);
        assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.IN_PROGRESS);
    }

    @Test
    void testGetTaskList() {
        TaskList list = TaskList.builder().id(2L).title("List").position(1).build();
        Task task = new Task();
        task.setTaskList(list);
        assertThat(task.getTaskList()).isEqualTo(list);
    }

    @Test
    void testGetTitle() {
        Task task = new Task();
        task.setTitle("Title");
        assertThat(task.getTitle()).isEqualTo("Title");
    }

    @Test
    void testSetAssignees() {
        User user = User.builder().id(1L).username("user").email("user@example.com").build();
        Task task = new Task();
        task.setAssignees(Set.of(user));
        assertThat(task.getAssignees()).containsExactly(user);
    }

    @Test
    void testSetDescription() {
        Task task = new Task();
        task.setDescription("desc");
        assertThat(task.getDescription()).isEqualTo("desc");
    }

    @Test
    void testSetId() {
        Task task = new Task();
        task.setId(99L);
        assertThat(task.getId()).isEqualTo(99L);
    }

    @Test
    void testSetStatus() {
        Task task = new Task();
        task.setStatus(Task.TaskStatus.DONE);
        assertThat(task.getStatus()).isEqualTo(Task.TaskStatus.DONE);
    }

    @Test
    void testSetTaskList() {
        TaskList list = TaskList.builder().id(3L).title("Another List").position(2).build();
        Task task = new Task();
        task.setTaskList(list);
        assertThat(task.getTaskList()).isEqualTo(list);
    }

    @Test
    void testSetTitle() {
        Task task = new Task();
        task.setTitle("New Title");
        assertThat(task.getTitle()).isEqualTo("New Title");
    }
}