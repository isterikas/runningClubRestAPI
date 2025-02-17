package lt.techin.running_club.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record RunningEventRequestDTO(
        @NotNull(message = "Name cannot be null.")
        @Length(min = 5, message = "Name must be at least 5 characters long.") String name,
        @NotNull(message = "Date cannot be null.")
        @Future(message = "Has to be a future date.")
        LocalDate calendarDate,
        @NotNull(message = "Location cannot be null.")
        @Pattern(regexp = "^[A-Za-z0-9_ \\-]+$", message = "Location can only contain letters and numbers.")
        String location,
        @Min(value = 1, message = "Cannot be 0 or a negative number.")
        int maxParticipants) {
}
