package lt.techin.running_club.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
  String message() default "Username is already taken.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
