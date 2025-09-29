package de.fherold.task_manager.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a TaskList entity.
 * Contains fields for task list details such as name and position.
 */

@Entity
@Table(name = "task_lists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer position;
}
