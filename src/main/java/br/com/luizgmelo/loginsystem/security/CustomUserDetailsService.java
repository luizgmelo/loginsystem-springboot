package br.com.luizgmelo.loginsystem.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.luizgmelo.loginsystem.models.User;
import br.com.luizgmelo.loginsystem.repositories.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
        new ArrayList<>());
  }

}
