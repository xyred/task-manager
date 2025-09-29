package de.fherold.task_manager.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a Task entity.
 * Contains fields for task details and status.
 */

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_title", nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE
    }
}
