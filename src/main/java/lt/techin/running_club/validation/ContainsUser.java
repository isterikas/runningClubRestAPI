package lt.techin.running_club.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ContainsUserValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsUser {

  String message() default "User role is mandatory.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
