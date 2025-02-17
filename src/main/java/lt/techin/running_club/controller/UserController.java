package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.*;
import lt.techin.running_club.model.User;
import lt.techin.running_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/auth/register")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
    User savedUser = userService.saveUser(userRequestDTO);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri()).body(UserMapper.toUserResponseDTO(savedUser));
  }
}
