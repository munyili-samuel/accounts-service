package com.bringglobal.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements CustomerService {
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return Constants.users.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst()
        .map(user -> new User(
          user.getUsername(),
          user.getPassword(),
          true,
          true,
          true,
          false,
          user.getRole()
    ))
        .orElse(null);
  }
}
