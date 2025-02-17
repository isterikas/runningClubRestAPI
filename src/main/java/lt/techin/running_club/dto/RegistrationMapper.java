package lt.techin.running_club.dto;

import lt.techin.running_club.model.Registration;

public class RegistrationMapper {

  public static RegistrationResponseDTO toRegistrationResponseDTO(Registration registration) {
    return new RegistrationResponseDTO(registration.getId(), registration.getUser().getId(),
            registration.getRunningEvent().getName(), registration.getRegistrationDate());
  }
}
