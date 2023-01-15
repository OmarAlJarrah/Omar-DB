package com.omar.model.access.impl;

import com.google.gson.Gson;
import com.omar.model.access.abstraction.Permission;
import com.omar.model.db.impl.metadata.Id;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class User {
  private String name;
  private String username;
  private String password;
  private Role role;


  private User() {
  }


  private final Map<String, Permission> permissions = new ConcurrentHashMap<>();

  public void addPermission(Permission permission) {
    permissions.putIfAbsent(permission.getResource().getName(), permission);
  }

  public void updatePermission(Permission permission) {
    permissions.put(permission.getResource().getName(), permission);
  }


  public Permission getPermission(String resourceKey) {
    return permissions.get(resourceKey);
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }


  public Role getRole() {
    return role;
  }

  public Id getId() {
    return new Id(UUID.fromString(username));
  }

  public String getName() {
    return name;
  }

  private void setUsername(String username) {
    this.username = username;
  }

  private void setPassword(String password) {
    this.password = password;
  }

  private void setName(String name) {
    this.name = name;
  }

  private void setRole(Role role) {
    this.role = role;
  }

  public static class UserBuilder {
    private User instance;

    private void setupInstance() {
      if (this.instance == null) {
        this.instance = new User();
      }
    }

    public UserBuilder username(String username) {
      setupInstance();
      instance.setUsername(username);
      return this;
    }

    public UserBuilder password(String password) {
      setupInstance();
      instance.setPassword(password);
      return this;
    }

    public UserBuilder role(Role role) {
      setupInstance();
      instance.setRole(role);
      return this;
    }

    public UserBuilder name(String name) {
      setupInstance();
      instance.setName(name);
      return this;
    }

    private boolean isValidUser() {
      return !instance.getUsername().isEmpty()
          && !(instance.password.length() < 6)
          && !instance.name.isEmpty()
          && !(instance.role == null);
    }

    public User build() {
      if (isValidUser()) {
        return instance;
      }
      return null;
    }
  }

}
