package lt.techin.running_club.service;

import lt.techin.running_club.dto.RegistrationRequestDTO;
import lt.techin.running_club.dto.UserRequestDTO;
import lt.techin.running_club.model.Role;
import lt.techin.running_club.model.User;
import lt.techin.running_club.repository.RoleRepository;
import lt.techin.running_club.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User saveUser(UserRequestDTO userRequestDTO) {
    List<Role> roles = userRequestDTO.roles().stream().map(role -> roleRepository.findById(role.getId()).get()).toList();
    User user = new User(userRequestDTO.username().toLowerCase(), passwordEncoder.encode(userRequestDTO.password()), roles);
    userRepository.save(user);
    return user;
  }

  public Optional<User> findByUsername(String username) {
    return userRepository.findUserByUsername(username);
  }

  public Optional<User> findById(long id) {
    return userRepository.findById(id);
  }
}
