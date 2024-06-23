package br.com.luizgmelo.loginsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @GetMapping
  public ResponseEntity<String> getUser() {
    return ResponseEntity.status(HttpStatus.OK).body("Sucesso você está logado !");
  }
}
