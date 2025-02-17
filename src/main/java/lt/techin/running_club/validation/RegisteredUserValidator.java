package lt.techin.running_club.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lt.techin.running_club.model.Registration;
import lt.techin.running_club.model.User;
import lt.techin.running_club.repository.RegistrationRepository;
import lt.techin.running_club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RegisteredUserValidator implements ConstraintValidator<RegisteredUser, User> {

  private final RegistrationRepository registrationRepository;

  @Autowired
  public RegisteredUserValidator(RegistrationRepository registrationRepository) {
    this.registrationRepository = registrationRepository;
  }

  @Override
  public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
    return registrationRepository.findAll().stream().noneMatch(event -> event.getUser().getId() == user.getId());

  }
}
