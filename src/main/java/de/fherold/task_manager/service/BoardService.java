package de.fherold.task_manager.service;

import de.fherold.task_manager.dto.BoardDto;
import de.fherold.task_manager.model.Board;
import de.fherold.task_manager.repository.BoardRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Service class for managing boards.
 * Provides methods to create and manipulate boards.
 */

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board toEntity(BoardDto dto) {
        if (dto == null)
            return null;
        return Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public BoardDto toDto(Board board) {
        if (board == null)
            return null;
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .build();
    }

    public BoardDto createBoard(BoardDto boardDto) {
        Board board = toEntity(boardDto);
        Board saved = boardRepository.save(board);
        return toDto(saved);
    }

    public BoardDto getBoard(Long id) {
        return boardRepository.findById(id).map(this::toDto).orElse(null);
    }

    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream().map(this::toDto).toList();
    }

    public BoardDto updateBoard(Long id, BoardDto boardDto) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isEmpty()) {
            return null;
        }
        Board board = optionalBoard.get();
        board.setTitle(boardDto.getTitle());
        board.setDescription(boardDto.getDescription());
        Board updated = boardRepository.save(board);
        return toDto(updated);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
