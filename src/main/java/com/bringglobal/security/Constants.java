package com.bringglobal.security;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.Data;

public class Constants {
  public static List<User> users = Arrays.asList(
    new User(user.getUsername(), "jane", true, true, true, false, "jane"),
    new User(user.getUsername(), "peter", true, true, true, false, "peter"),
    new User(user.getUsername(), "john", true, true, true, false, "john"),
    new User(user.getUsername(), "sara", true, true, true, false, "sare")
  );

  public static User findUserById(String token) {
    return users.stream().filter(u -> u.getId().equals(token)).findFirst().orElse(null);
  }

}

@Data
class User {
  private String id;
  private String username;
  private String password;
  private String role = "USER";

  User(
    String userUsername, String username,
    boolean b3, boolean b2, boolean b1, boolean b, String password
  ) {
    this.id = UUID.fromString(username).toString();
    this.username = username;
    this.password = password;
  }
}
