package lt.techin.running_club.controller;

import lt.techin.running_club.repository.RunningEventRepository;
import lt.techin.running_club.service.RunningEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RunningEventController {

  private final RunningEventService runningEventService;

  @Autowired
  public RunningEventController(RunningEventService runningEventService) {
    this.runningEventService = runningEventService;
  }
}
