package tech.com.agregadordeinvestimentos.service;

import java.time.Instant;
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
}
