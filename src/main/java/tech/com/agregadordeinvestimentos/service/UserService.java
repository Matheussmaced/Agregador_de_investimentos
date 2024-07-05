package tech.com.agregadordeinvestimentos.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import tech.com.agregadordeinvestimentos.controller.CreateUserDto;
import tech.com.agregadordeinvestimentos.entity.User;
import tech.com.agregadordeinvestimentos.repository.UserRepository;

@Service
public class UserService {

  private UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Post

  public UUID createUser(CreateUserDto createUserDto) {
    // DTO -> ENTITY
    var entity = new User(
        UUID.randomUUID(),
        createUserDto.username(),
        createUserDto.email(),
        createUserDto.password(),
        Instant.now(),
        null);

    var userSaved = userRepository.save(entity);

    return userSaved.getUserId();
  }

  // Get

  public Optional<User> getUserById(String userId) {
    return userRepository.findById(UUID.fromString(userId));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Delete

  public void deleteUserById(String userId) {
    var id = UUID.fromString(userId);

    var userExist = userRepository.existsById(id);

    if (userExist) {
      userRepository.deleteById(id);
    }
  }
}
