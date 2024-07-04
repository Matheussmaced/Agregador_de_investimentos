package tech.com.agregadordeinvestimentos.controller;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody String body) {
    //
    return null;
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
    //
    return null;
  }
}
