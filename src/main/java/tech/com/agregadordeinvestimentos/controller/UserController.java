package tech.com.agregadordeinvestimentos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.com.agregadordeinvestimentos.DTO.CreateUserDto;
import tech.com.agregadordeinvestimentos.DTO.UpdateUserDto;
import tech.com.agregadordeinvestimentos.entity.User;
import tech.com.agregadordeinvestimentos.service.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
    var userId = userService.createUser(createUserDto);
    return ResponseEntity.created(URI.create("/v1/users/" + userId.toString())).build();
  }

  // http://localhost:8080/v1/users/{id}
  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
    var user = userService.getUserById(userId);
    if (user.isPresent()) {
      return ResponseEntity.ok(user.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // http://localhost:8080/v1/users/allUsers
  @GetMapping("/allUsers")
  public ResponseEntity<List<User>> getAllUsers() {
    var users = userService.getAllUsers();

    return ResponseEntity.ok(users);
  }

  @PutMapping("/{userId}")
  public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId,
      @RequestBody UpdateUserDto updateUserDto) {
    userService.updateUserById(userId, updateUserDto);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<Void> deleteById(@PathVariable("userId") String userId) {
    userService.deleteUserById(userId);

    return ResponseEntity.noContent().build();
  }
}
