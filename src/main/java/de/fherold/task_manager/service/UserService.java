package de.fherold.task_manager.service;

import de.fherold.task_manager.dto.UserDto;
import de.fherold.task_manager.model.User;
import de.fherold.task_manager.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing users.
 * Provides methods to create and manipulate users.
 */

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User toEntity(UserDto dto) {
        if (dto == null)
            return null;
        return User.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .build();
    }

    public UserDto toDto(User user) {
        if (user == null)
            return null;
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

    public UserDto createUser(UserDto dto) {
        User user = toEntity(dto);
        User saved = userRepository.save(user);
        return toDto(saved);
    }

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return toDto(user);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).toList();
    }

    public UserDto updateUser(Long id, UserDto dto) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());

        User updated = userRepository.save(user);
        return toDto(updated);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
