package com.omar.service;

import com.google.gson.Gson;
import com.omar.file.Reader;
import com.omar.model.access.impl.User;
import com.omar.model.db.impl.metadata.Id;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private Reader reader;

  private final Gson gson = new Gson();

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    JSONObject jsonObject = (JSONObject) reader.getJsonObject("USERS", new Id(UUID.fromString(username)));
    if (jsonObject.getJSONObject(username).isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }

    User user = gson.fromJson(
        jsonObject.getJSONObject(username).toString(),
        User.class
    );

    return new org.springframework.security.core.userdetails.User(
        user.getUsername(),
        user.getPassword(),
        List.of(new SimpleGrantedAuthority(user.getRole().role))
    );
  }
}
