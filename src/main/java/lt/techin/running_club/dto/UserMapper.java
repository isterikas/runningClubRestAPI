package lt.techin.running_club.dto;

import lt.techin.running_club.model.User;

public class UserMapper {


  public static UserResponseDTO toUserResponseDTO(User user) {


    return new UserResponseDTO(user.getId(), user.getUsername(), RoleMapper.roleResponseDTOList(user.getRoles()));
  }

}
