package br.com.luizgmelo.loginsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizgmelo.loginsystem.dtos.UserDto;
import br.com.luizgmelo.loginsystem.dtos.UserResponseDto;
import br.com.luizgmelo.loginsystem.models.User;
import br.com.luizgmelo.loginsystem.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserDto userDto) {
    User user = userService.register(userDto);
    UserResponseDto response = new UserResponseDto(user.getUsername());
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @GetMapping("/users")
  public User getAll(@RequestBody UserResponseDto user) {
    return userService.getAll(user.username());
  }
}
