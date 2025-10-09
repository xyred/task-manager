package de.fherold.task_manager.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import de.fherold.task_manager.dto.BoardDto;
import de.fherold.task_manager.service.BoardService;

public class BoardControllerTest {

    private final BoardService boardService = Mockito.mock(BoardService.class);
    private final BoardController boardController = new BoardController(boardService);

    @Test
    void testCreateBoard() {
        BoardDto dto = BoardDto.builder().title("New Board").description("Desc").build();
        BoardDto saved = BoardDto.builder().id(1L).title("New Board").description("Desc").build();
        Mockito.when(boardService.createBoard(dto)).thenReturn(saved);

        ResponseEntity<BoardDto> response = boardController.createBoard(dto);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(saved);
    }

    @Test
    void testDeleteBoard() {
        ResponseEntity<Void> response = boardController.deleteBoard(1L);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
    }

    @Test
    void testGetAllBoards() {
        BoardDto dto1 = BoardDto.builder().id(1L).title("Board1").build();
        BoardDto dto2 = BoardDto.builder().id(2L).title("Board2").build();
        Mockito.when(boardService.getAllBoards()).thenReturn(List.of(dto1, dto2));

        ResponseEntity<List<BoardDto>> response = boardController.getAllBoards();

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).containsExactly(dto1, dto2);
    }

    @Test
    void testGetBoard() {
        BoardDto dto = BoardDto.builder().id(1L).title("Board1").build();
        Mockito.when(boardService.getBoard(1L)).thenReturn(dto);

        ResponseEntity<BoardDto> response = boardController.getBoard(1L);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(dto);
    }

    @Test
    void testUpdateBoard() {
        BoardDto dto = BoardDto.builder().title("Updated").build();
        BoardDto updated = BoardDto.builder().id(1L).title("Updated").build();
        Mockito.when(boardService.updateBoard(1L, dto)).thenReturn(updated);

        ResponseEntity<BoardDto> response = boardController.updateBoard(1L, dto);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isEqualTo(updated);
    }
}