package lt.techin.running_club.dto;

import lt.techin.running_club.dto.RoleResponseDTO;
import lt.techin.running_club.model.Role;

import java.util.List;

public class RoleMapper {

  public static RoleResponseDTO toRoleResponseDTO(Role role) {
    return new RoleResponseDTO(role.getId(), role.getName());
  }

  public static List<RoleResponseDTO> roleResponseDTOList(List<Role> roles) {
    return roles.stream().map(RoleMapper::toRoleResponseDTO).toList();
  }

}