package lt.techin.running_club.dto;

import lt.techin.running_club.model.RunningEvent;

public class RunningEventMapper {

  public static RunningEventResponseDTO toRunningEventResponseDTO(RunningEvent runningEvent) {
    return new RunningEventResponseDTO(runningEvent.getId(), runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants());
  }


}
