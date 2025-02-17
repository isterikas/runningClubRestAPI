package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.RegistrationMapper;
import lt.techin.running_club.dto.RegistrationRequestDTO;
import lt.techin.running_club.dto.RegistrationResponseDTO;
import lt.techin.running_club.dto.RunningEventMapper;
import lt.techin.running_club.model.Registration;
import lt.techin.running_club.model.RunningEvent;
import lt.techin.running_club.model.User;
import lt.techin.running_club.service.RegistrationService;
import lt.techin.running_club.service.RunningEventService;
import lt.techin.running_club.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RegistrationController {

  private final RegistrationService registrationService;
  private final UserService userService;
  private final RunningEventService runningEventService;

  @Autowired
  public RegistrationController(RegistrationService registrationService, UserService userService, RunningEventService runningEventService) {
    this.registrationService = registrationService;
    this.userService = userService;
    this.runningEventService = runningEventService;
  }

  @PostMapping("/events/{eventId}/register")
  public ResponseEntity<?> registerForEvent(@PathVariable long eventId, @Valid @RequestBody RegistrationRequestDTO registrationRequestDTO, Authentication authentication) {
    Optional<RunningEvent> runningEvent = runningEventService.findById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event with such ID does not exist.");

    }
    Optional<User> user = userService.findById(registrationRequestDTO.user().getId());
    if (user.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with such ID does not exist.");
    }
    if (((User) authentication.getPrincipal()).getId() != registrationRequestDTO.user().getId()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can only register yourself to an event.");
    }
//    if (registrationService.findAll().stream().anyMatch(event -> event.getUser().getId() == registrationRequestDTO.user().getId())) {
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is already registered.");
//
//    }
    Registration registration = new Registration();
    registration.setUser(user.get());
    registration.setRunningEvent(runningEvent.get());
    registration.setRegistrationDate(LocalDateTime.now());
    registrationService.save(registration);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(registration.getId())
            .toUri()).body(RegistrationMapper.toRegistrationResponseDTO(registration));
  }
}
