package de.fherold.task_manager.model;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TaskListTest {
    @Test
    void testBuilder() {
        Board board = Board.builder().id(1L).title("Board").description("Desc").build();
        Task task = Task.builder().id(1L).title("Task").build();

        TaskList taskList = TaskList.builder()
                .id(10L)
                .title("List Title")
                .position(2)
                .board(board)
                .tasks(List.of(task))
                .build();

        assertThat(taskList.getId()).isEqualTo(10L);
        assertThat(taskList.getTitle()).isEqualTo("List Title");
        assertThat(taskList.getPosition()).isEqualTo(2);
        assertThat(taskList.getBoard()).isEqualTo(board);
        assertThat(taskList.getTasks()).containsExactly(task);
    }

    @Test
    void testGetBoard() {
        Board board = Board.builder().id(2L).title("Board2").build();
        TaskList taskList = new TaskList();
        taskList.setBoard(board);
        assertThat(taskList.getBoard()).isEqualTo(board);
    }

    @Test
    void testGetId() {
        TaskList taskList = new TaskList();
        taskList.setId(99L);
        assertThat(taskList.getId()).isEqualTo(99L);
    }

    @Test
    void testGetPosition() {
        TaskList taskList = new TaskList();
        taskList.setPosition(5);
        assertThat(taskList.getPosition()).isEqualTo(5);
    }

    @Test
    void testGetTasks() {
        Task task = Task.builder().id(3L).title("Task3").build();
        TaskList taskList = new TaskList();
        taskList.setTasks(List.of(task));
        assertThat(taskList.getTasks()).containsExactly(task);
    }

    @Test
    void testGetTitle() {
        TaskList taskList = new TaskList();
        taskList.setTitle("My List");
        assertThat(taskList.getTitle()).isEqualTo("My List");
    }

    @Test
    void testSetBoard() {
        Board board = Board.builder().id(3L).title("Board3").build();
        TaskList taskList = new TaskList();
        taskList.setBoard(board);
        assertThat(taskList.getBoard()).isEqualTo(board);
    }

    @Test
    void testSetId() {
        TaskList taskList = new TaskList();
        taskList.setId(123L);
        assertThat(taskList.getId()).isEqualTo(123L);
    }

    @Test
    void testSetPosition() {
        TaskList taskList = new TaskList();
        taskList.setPosition(7);
        assertThat(taskList.getPosition()).isEqualTo(7);
    }

    @Test
    void testSetTasks() {
        Task task = Task.builder().id(4L).title("Task4").build();
        TaskList taskList = new TaskList();
        taskList.setTasks(List.of(task));
        assertThat(taskList.getTasks()).containsExactly(task);
    }

    @Test
    void testSetTitle() {
        TaskList taskList = new TaskList();
        taskList.setTitle("Set Title");
        assertThat(taskList.getTitle()).isEqualTo("Set Title");
    }
}