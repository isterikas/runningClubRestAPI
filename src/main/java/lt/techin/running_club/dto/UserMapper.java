package lt.techin.running_club.dto;

import lt.techin.running_club.model.User;

import java.util.List;

public class UserMapper {


  public static UserResponseDTO toUserResponseDTO(User user) {
    return new UserResponseDTO(user.getId(), user.getUsername(), RoleMapper.roleResponseDTOList(user.getRoles()));
  }

  public static List<UserResponseDTO> toUserResponseDTOList(List<User> users) {
    return users.stream().map(UserMapper::toUserResponseDTO).toList();
  }

  public static UserAsParticipantResponseDTO toUserAsParticipantResponseDTO(User user) {
    return new UserAsParticipantResponseDTO(user.getId(), user.getUsername());
  }

  public static List<UserAsParticipantResponseDTO> toUserAsParticipantResponseDTOList(List<User> users) {
    return users.stream().map(UserMapper::toUserAsParticipantResponseDTO).toList();
  }
}
