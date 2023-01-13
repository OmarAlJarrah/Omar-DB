package com.omar.model.access.implementation;

import com.omar.constant.Concurrency;
import com.omar.model.access.abstraction.Permission;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class User {
  public String firstName;
  public String lastName;

  private String userId;
  private String userSecret;

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
}
