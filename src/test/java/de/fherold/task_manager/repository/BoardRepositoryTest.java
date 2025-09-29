package de.fherold.task_manager.repository;

import de.fherold.task_manager.model.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void testSaveAndFindBoard() {
        Board board = Board.builder()
                .name("Team Board")
                .description("Board for team collaboration")
                .build();

        Board saved = boardRepository.save(board);
        assertThat(saved.getId()).isNotNull();

        Board found = boardRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Team Board");
        assertThat(found.getDescription()).isEqualTo("Board for team collaboration");
    }
}
