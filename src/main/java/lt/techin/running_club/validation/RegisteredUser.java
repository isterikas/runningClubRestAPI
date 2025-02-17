package lt.techin.running_club.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegisteredUserValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RegisteredUser {
  String message() default "User is already registered.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
