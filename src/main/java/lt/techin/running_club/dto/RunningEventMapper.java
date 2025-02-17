package lt.techin.running_club.dto;

import lt.techin.running_club.model.RunningEvent;

import java.util.List;

public class RunningEventMapper {

  public static RunningEventResponseDTO toRunningEventResponseDTO(RunningEvent runningEvent) {
    return new RunningEventResponseDTO(runningEvent.getId(), runningEvent.getName(), runningEvent.getCalendarDate(), runningEvent.getLocation(), runningEvent.getMaxParticipants());
  }

  public static List<RunningEventResponseDTO> toRunningEventResponseDTOList(List<RunningEvent> runningEvents) {
    return runningEvents.stream().map(RunningEventMapper::toRunningEventResponseDTO).toList();
  }


}
