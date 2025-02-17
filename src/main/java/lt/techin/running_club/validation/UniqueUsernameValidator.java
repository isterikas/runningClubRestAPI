package lt.techin.running_club.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lt.techin.running_club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
  private final UserRepository userRepository;

  @Autowired
  public UniqueUsernameValidator(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
    return userRepository.findUserByUsername(username.toLowerCase()).isEmpty();
  }
}
