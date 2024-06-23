package br.com.luizgmelo.loginsystem.security;

import br.com.luizgmelo.loginsystem.models.User;

public interface ITokenService {
  String generateToken(User user);

  String validateToken(String token);
}
