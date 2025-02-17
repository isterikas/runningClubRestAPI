package lt.techin.running_club.controller;

import jakarta.validation.Valid;
import lt.techin.running_club.dto.RunningEventMapper;
import lt.techin.running_club.dto.RunningEventRequestDTO;
import lt.techin.running_club.dto.UserMapper;
import lt.techin.running_club.model.RunningEvent;
import lt.techin.running_club.service.RunningEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  private final RunningEventService runningEventService;

  @Autowired
  public RunningEventController(RunningEventService runningEventService) {
    this.runningEventService = runningEventService;
  }

  @PostMapping("/events")
  public ResponseEntity<?> createEvent(@Valid @RequestBody RunningEventRequestDTO runningEventRequestDTO) {
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
}
