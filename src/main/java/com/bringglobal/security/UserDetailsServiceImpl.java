package com.bringglobal.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
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
          Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
    ))
        .orElse(null);
  }
}
