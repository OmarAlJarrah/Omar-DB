package com.omar.api;

import com.google.gson.Gson;
import com.omar.model.access.impl.Role;
import com.omar.model.access.impl.User;
import com.omar.model.exception.UserAlreadyExistsException;
import com.omar.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("/users")
public class UsersManagementApi {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  UserManagementService service;

  private final Gson gson = new Gson();

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestParam("role") String role, @RequestParam("name") String name) {
    ResponseEntity<String> response;
    try {
      User user = new User.UserBuilder()
          .name(name)
          .role(Role.valueOf(role))
          .username(UUID.randomUUID().toString())
          .password(UUID.randomUUID().toString())
          .build();

      service.addUser(user);
      response = new ResponseEntity<>(gson.toJson(user), HttpStatus.ACCEPTED);
    } catch (UserAlreadyExistsException e) {
      response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    return response;
  }
}
