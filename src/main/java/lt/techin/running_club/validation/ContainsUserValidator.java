package lt.techin.running_club.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lt.techin.running_club.model.Role;

import java.util.List;

public class ContainsUserValidator implements ConstraintValidator<ContainsUser, List<Role>> {


  @Override
  public boolean isValid(List<Role> roleList, ConstraintValidatorContext constraintValidatorContext) {
    return roleList.stream().anyMatch(role -> role.getId() == 1);
  }
}
