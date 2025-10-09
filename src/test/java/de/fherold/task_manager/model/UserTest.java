package de.fherold.task_manager.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    void testBuilder() {
        Board board = Board.builder().id(1L).title("Board").build();
        Task task = Task.builder().id(2L).title("Task").build();

        User user = User.builder()
                .id(10L)
                .username("testuser")
                .email("test@example.com")
                .password("secret")
                .boards(Set.of(board))
                .tasks(Set.of(task))
                .build();

        assertThat(user.getId()).isEqualTo(10L);
        assertThat(user.getUsername()).isEqualTo("testuser");
        assertThat(user.getEmail()).isEqualTo("test@example.com");
        assertThat(user.getPassword()).isEqualTo("secret");
        assertThat(user.getBoards()).containsExactly(board);
        assertThat(user.getTasks()).containsExactly(task);
    }

    @Test
    void testGetBoards() {
        Board board = Board.builder().id(2L).title("Board2").build();
        User user = new User();
        user.setBoards(Set.of(board));
        assertThat(user.getBoards()).containsExactly(board);
    }

    @Test
    void testGetEmail() {
        User user = new User();
        user.setEmail("mail@example.com");
        assertThat(user.getEmail()).isEqualTo("mail@example.com");
    }

    @Test
    void testGetId() {
        User user = new User();
        user.setId(99L);
        assertThat(user.getId()).isEqualTo(99L);
    }

    @Test
    void testGetPassword() {
        User user = new User();
        user.setPassword("pw");
        assertThat(user.getPassword()).isEqualTo("pw");
    }

    @Test
    void testGetTasks() {
        Task task = Task.builder().id(3L).title("Task3").build();
        User user = new User();
        user.setTasks(Set.of(task));
        assertThat(user.getTasks()).containsExactly(task);
    }

    @Test
    void testGetUsername() {
        User user = new User();
        user.setUsername("uname");
        assertThat(user.getUsername()).isEqualTo("uname");
    }

    @Test
    void testSetBoards() {
        Board board = Board.builder().id(4L).title("Board4").build();
        User user = new User();
        user.setBoards(Set.of(board));
        assertThat(user.getBoards()).containsExactly(board);
    }

    @Test
    void testSetEmail() {
        User user = new User();
        user.setEmail("set@example.com");
        assertThat(user.getEmail()).isEqualTo("set@example.com");
    }

    @Test
    void testSetId() {
        User user = new User();
        user.setId(123L);
        assertThat(user.getId()).isEqualTo(123L);
    }

    @Test
    void testSetPassword() {
        User user = new User();
        user.setPassword("setpw");
        assertThat(user.getPassword()).isEqualTo("setpw");
    }

    @Test
    void testSetTasks() {
        Task task = Task.builder().id(5L).title("Task5").build();
        User user = new User();
        user.setTasks(Set.of(task));
        assertThat(user.getTasks()).containsExactly(task);
    }

    @Test
    void testSetUsername() {
        User user = new User();
        user.setUsername("setuname");
        assertThat(user.getUsername()).isEqualTo("setuname");
    }
}