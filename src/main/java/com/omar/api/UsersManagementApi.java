package com.omar.api;

import com.google.gson.Gson;
import com.omar.model.access.impl.Role;
import com.omar.model.access.impl.User;
import com.omar.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersManagementApi {
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
      response = new ResponseEntity<>(gson.toJson(user), HttpStatus.OK);
    } catch (Exception e) {
      response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  @PostMapping("/delete")
  public ResponseEntity<String> deleteUser(@RequestParam("username") String username) {
    try {
      service.deleteUser(username);
    } catch (IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
