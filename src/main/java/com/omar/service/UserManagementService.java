package com.omar.service;

import com.omar.constant.Constant;
import com.omar.file.ConcurrentWriter;
import com.omar.file.Reader;
import com.omar.model.access.impl.User;
import com.omar.model.exception.UserAlreadyExistsException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
  @Autowired
  Reader reader;

  @Autowired
  ConcurrentWriter writer;

  public void addUser(User user) throws UserAlreadyExistsException {
    JSONObject other = ((JSONObject) reader.getJsonObject(Constant.USERS.name(), user.getId())).getJSONObject(user.getUsername());
    boolean userNotExists = other == null || other.isEmpty();
    if (!userNotExists) {
      throw new UserAlreadyExistsException();
    }

//   TODO: Setup authorization
//    user.addPermission();
    writer.writeUser(new JSONObject(user));
  }
}
