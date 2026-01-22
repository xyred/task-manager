package de.fherold.task_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.fherold.task_manager.dto.BoardDto;
import de.fherold.task_manager.exception.BoardNotFoundException;
import de.fherold.task_manager.model.Board;
import de.fherold.task_manager.model.TaskList;
import de.fherold.task_manager.model.User;
import de.fherold.task_manager.repository.BoardRepository;
import de.fherold.task_manager.repository.TaskListRepository;
import de.fherold.task_manager.repository.UserRepository;

/**
 * Service class for managing boards.
 * Provides methods to create and manipulate boards.
 */

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final TaskListRepository taskListRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository, TaskListRepository taskListRepository,
            UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.taskListRepository = taskListRepository;
        this.userRepository = userRepository;
    }

    public Board toEntity(BoardDto dto) {
        if (dto == null)
            return null;

        List<TaskList> taskLists = dto.getTaskListIds() == null ? List.of()
                : dto.getTaskListIds().stream()
                        .map(taskListRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList();

        Set<User> users = dto.getMemberIds() == null ? Set.of()
                : dto.getMemberIds().stream()
                        .map(userRepository::findById)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet());

        return Board.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .taskLists(taskLists)
                .users(users)
                .build();
    }

    public BoardDto toDto(Board board) {
        if (board == null)
            return null;

        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .taskListIds(board.getTaskLists() == null ? List.of()
                        : board.getTaskLists().stream()
                                .filter(tl -> tl != null && tl.getId() != null)
                                .map(TaskList::getId)
                                .toList())
                .memberIds(board.getUsers() == null ? Set.of()
                        : board.getUsers().stream()
                                .filter(u -> u != null && u.getId() != null)
                                .map(User::getId)
                                .collect(Collectors.toSet()))
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
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException(id));

        if (boardDto.getTitle() != null && !boardDto.getTitle().isBlank()) {
            board.setTitle(boardDto.getTitle());
        }

        if (boardDto.getDescription() != null) {
            board.setDescription(boardDto.getDescription());
        }

        Board updated = boardRepository.save(board);
        return toDto(updated);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}
