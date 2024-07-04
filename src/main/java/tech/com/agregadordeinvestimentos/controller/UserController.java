package tech.com.agregadordeinvestimentos.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }
}
