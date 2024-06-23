package br.com.luizgmelo.loginsystem.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luizgmelo.loginsystem.dtos.LoginRegisterRequestDTO;
import br.com.luizgmelo.loginsystem.dtos.ResponseDto;
import br.com.luizgmelo.loginsystem.dtos.LoginRegisterRequestDTO;
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
  public ResponseEntity register(@RequestBody @Valid LoginRegisterRequestDTO body) {
    String token = userService.register(body);
    if (token == null)
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(body.username(), token));
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody @Valid LoginRegisterRequestDTO body) {
    String token = userService.loginAcess(body);
    if (token == null)
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong username or password!");
    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(body.username(), token));
  }
}
