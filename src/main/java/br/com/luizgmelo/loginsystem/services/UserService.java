package br.com.luizgmelo.loginsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizgmelo.loginsystem.dtos.UserDto;
import br.com.luizgmelo.loginsystem.models.User;
import br.com.luizgmelo.loginsystem.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  public User register(UserDto userDto) {
    String encodedPassword = this.passwordEncoder.encode(userDto.password());
    User newUser = new User(userDto.username(), encodedPassword);

    return userRepository.save(newUser);
  }

  public User getAll(String username) {
    return userRepository.findByUsername(username).get();
  }

}
