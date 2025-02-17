package lt.techin.running_club.dto;

import java.util.List;

public record UserResponseDTO(
        long id,
        String username,
        List<RoleResponseDTO> roles) {


}
