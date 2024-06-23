package br.com.luizgmelo.loginsystem.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luizgmelo.loginsystem.dtos.LoginRegisterRequestDTO;
import br.com.luizgmelo.loginsystem.dtos.LoginRegisterRequestDTO;
import br.com.luizgmelo.loginsystem.models.User;
import br.com.luizgmelo.loginsystem.repositories.UserRepository;
import br.com.luizgmelo.loginsystem.security.TokenService;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TokenService tokenService;

  public String register(LoginRegisterRequestDTO body) {
    Optional<User> user = userRepository.findByUsername(body.username());
    if (user.isEmpty()) {
      String encodedPassword = this.passwordEncoder.encode(body.password());
      User newUser = new User(body.username(), encodedPassword);
      userRepository.save(newUser);
      String token = tokenService.generateToken(newUser);
      return token;
    }
    return null;
  }

  public String loginAcess(LoginRegisterRequestDTO body) {
    User user = userRepository.findByUsername(body.username())
        .orElseThrow(() -> new RuntimeException("User not found"));

    if (passwordEncoder.matches(user.getPassword(), body.password())) {
      String token = tokenService.generateToken(user);
      return token;
    }

    return null;
  }

}
