package de.fherold.task_manager.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class BoardTest {
    @Test
    void testBuilder() {
        Board board = Board.builder()
                .id(1L)
                .title("Test Board")
                .description("Board Description")
                .taskLists(List.of())
                .users(Set.of())
                .build();

        assertThat(board.getId()).isEqualTo(1L);
        assertThat(board.getTitle()).isEqualTo("Test Board");
        assertThat(board.getDescription()).isEqualTo("Board Description");
        assertThat(board.getTaskLists()).isEmpty();
        assertThat(board.getUsers()).isEmpty();
    }

    @Test
    void testGetSetId() {
        Board board = new Board();
        board.setId(42L);
        assertThat(board.getId()).isEqualTo(42L);
    }

    @Test
    void testGetSetTitle() {
        Board board = new Board();
        board.setTitle("Board Title");
        assertThat(board.getTitle()).isEqualTo("Board Title");
    }

    @Test
    void testGetSetDescription() {
        Board board = new Board();
        board.setDescription("Desc");
        assertThat(board.getDescription()).isEqualTo("Desc");
    }

    @Test
    void testGetSetTaskLists() {
        Board board = new Board();
        List<TaskList> lists = List.of(TaskList.builder().id(1L).title("List").position(1).build());
        board.setTaskLists(lists);
        assertThat(board.getTaskLists()).isEqualTo(lists);
    }

    @Test
    void testGetSetUsers() {
        Board board = new Board();
        Set<User> users = Set.of(User.builder().id(1L).username("user").email("user@example.com").build());
        board.setUsers(users);
        assertThat(board.getUsers()).isEqualTo(users);
    }
}