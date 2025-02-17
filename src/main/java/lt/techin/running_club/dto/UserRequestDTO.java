package lt.techin.running_club.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lt.techin.running_club.model.Role;
import lt.techin.running_club.validation.ContainsUser;
import lt.techin.running_club.validation.UniqueUsername;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record UserRequestDTO(
        @NotNull(message = "Username cannot be null.")
        @Length(min = 4, message = "Username must be at least 4 characters long.")
        @Pattern(regexp = "^[a-z0-9\\-]+$", message = "Username can only contain lowercase letters and numbers.")
        @UniqueUsername(message = "Username already taken.")
        String username,
        @NotNull(message = "Password cannot be null.")
        @Length(min = 6, message = "Password must be at least 6 characters long.")
        String password,
        @NotNull(message = "User has to have at least one role.")
        @ContainsUser
        List<Role> roles
) {


}
