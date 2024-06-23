package br.com.luizgmelo.loginsystem.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.luizgmelo.loginsystem.models.User;

@Service
public class TokenService implements ITokenService {
  @Value("${api.security.token.secret}")
  private String secret;

  @Override
  public String generateToken(User user) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      String token = JWT.create()
          .withIssuer("login-system")
          .withSubject(user.getUsername())
          .withExpiresAt(this.generateExpirationDate())
          .sign(algorithm);
      return token;

    } catch (JWTCreationException ex) {
      throw new RuntimeException("Error while authenticating");
    }
  }

  @Override
  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);

      return JWT.require(algorithm)
          .withIssuer("login-system")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException ex) {
      return null;
    }
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
  }

}
