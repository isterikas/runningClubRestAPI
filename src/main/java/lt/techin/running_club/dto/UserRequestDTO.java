package lt.techin.running_club.dto;

import jakarta.validation.constraints.NotNull;
import lt.techin.running_club.model.Role;
import lt.techin.running_club.validation.UniqueUsername;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;


import java.util.List;

public record UserRequestDTO(
        @NotNull(message = "Username cannot be null.")
        @Length(min = 4, message = "Username must be at least 4 characters long.")
        @UniqueUsername
        String username,
        @NotNull(message = "Password cannot be null.")
        @Length(min = 10, message = "Password must be at least 10 characters long.")
        String password,
        @NonNull
        List<Role> roles
) {


}
