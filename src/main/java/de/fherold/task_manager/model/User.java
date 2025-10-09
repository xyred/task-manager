package de.fherold.task_manager.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a User entity.
 * Contains fields for user details such as username, email, and password.
 */

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Board> boards;

    @ManyToMany(mappedBy = "assignees")
    private Set<Task> tasks;
}
