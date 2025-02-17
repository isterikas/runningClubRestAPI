package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.*;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class RunningEventController {

  private final RunningEventService runningEventService;
  private final UserService userService;
  private final RegistrationService registrationService;

  @Autowired
  public RunningEventController(RunningEventService runningEventService, UserService userService, RegistrationService registrationService) {
    this.runningEventService = runningEventService;
    this.userService = userService;
    this.registrationService = registrationService;
  }

  @PostMapping
  public ResponseEntity<RunningEventResponseDTO> createEvent(@Valid @RequestBody RunningEventRequestDTO runningEventRequestDTO) {
    RunningEvent runningEvent = new RunningEvent();
    runningEvent.setName(runningEventRequestDTO.name());
    runningEvent.setCalendarDate(runningEventRequestDTO.calendarDate());
    runningEvent.setLocation(runningEventRequestDTO.location());
    runningEvent.setMaxParticipants(runningEventRequestDTO.maxParticipants());
    runningEventService.save(runningEvent);
    return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(runningEvent.getId())
            .toUri()).body(RunningEventMapper.toRunningEventResponseDTO(runningEvent));
  }

  @DeleteMapping("/{eventId}")
  public ResponseEntity<Void> deleteEvent(@PathVariable long eventId) {
    if (!runningEventService.existsById(eventId)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    runningEventService.deleteById(eventId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping
  public ResponseEntity<List<RunningEventResponseDTO>> getUpcomingEvents() {
    List<RunningEvent> upcomingEvents = runningEventService.getAllEvents().stream()
            .filter(event -> event.getCalendarDate().isAfter(LocalDate.now())).toList();
    return ResponseEntity.status(HttpStatus.OK).body(RunningEventMapper.toRunningEventResponseDTOList(upcomingEvents));
  }

  @GetMapping("/{eventId}/participants")
  public ResponseEntity<?> getUserList(@PathVariable long eventId) {
    Optional<RunningEvent> runningEvent = runningEventService.findById(eventId);
    if (runningEvent.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Event with such ID does not exist.");
    }
    List<User> participants = runningEvent.get().getRegistrations().stream()
            .map(Registration::getUser).toList();

    return ResponseEntity.status(HttpStatus.OK)
            .body(UserMapper.toUserAsParticipantResponseDTOList(participants));
  }

  @PostMapping("/{eventId}/register")
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
