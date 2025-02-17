package lt.techin.running_club.service;

import lt.techin.running_club.repository.RunningEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunningEventService {
  private RunningEventRepository runningEventRepository;

  @Autowired
  public RunningEventService(RunningEventRepository runningEventRepository) {
    this.runningEventRepository = runningEventRepository;
  }
}
