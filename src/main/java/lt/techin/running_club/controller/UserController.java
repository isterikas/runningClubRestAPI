package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.*;
import lt.techin.running_club.model.User;
import lt.techin.running_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UserController {

  private UserService userService;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserService userService, PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/users")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    if (userRequestDTO.roles().stream().anyMatch(role -> role.getId() == 1)) {
//      User savedUser = new User(userRequestDTO.username().toLowerCase(), passwordEncoder.encode(userRequestDTO.password()), userRequestDTO.roles());
      User savedUser = userService.saveUser(userRequestDTO);
      return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
              .path("/{id}")
              .buildAndExpand(savedUser.getId())
              .toUri()).body(UserMapper.toUserResponseDTO(savedUser));
    }
    return ResponseEntity.badRequest().body("User role is mandatory.");
  }
}
