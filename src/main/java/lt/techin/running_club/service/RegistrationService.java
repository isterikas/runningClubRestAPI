package lt.techin.running_club.service;

import lt.techin.running_club.model.Registration;
import lt.techin.running_club.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

  private RegistrationRepository registrationRepository;

  @Autowired
  public RegistrationService(RegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  public Registration save(Registration registration) {
    return registrationRepository.save(registration);
  }

  public List<Registration> findAll() {
    return registrationRepository.findAll();
  }
}
