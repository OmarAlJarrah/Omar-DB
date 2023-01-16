package com.omar.service;

import com.omar.constant.CollectionConstants;
import com.omar.file.ConcurrentWriter;
import com.omar.file.Reader;
import com.omar.model.access.impl.User;
import com.omar.model.exception.UserAlreadyExistsException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserManagementService {
  @Autowired
  private Reader reader;

  @Autowired
  private ConcurrentWriter writer;

  @Autowired
  private PasswordEncoder encoder;

  public void addUser(User user) throws UserAlreadyExistsException, IOException {
    user = new User.UserBuilder()
        .name(user.getName())
        .role(user.getRole())
        .username(user.getUsername())
        .password(encoder.encode(user.getPassword()))
        .build();

    JSONObject other = ((JSONObject) reader.getJsonObject(CollectionConstants.USERS.name(), user.getId())).getJSONObject(user.getUsername());
    boolean userNotExists = other == null || other.isEmpty();
    if (!userNotExists) {
      throw new UserAlreadyExistsException();
    }
//   TODO: Setup authorization
//    user.addPermission();
    writer.writeUser(new JSONObject(user));
  }

  public void deleteUser(String username) throws IOException {
    writer.deleteUser(username);
  }
}
