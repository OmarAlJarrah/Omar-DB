package com.omar.model.access.impl;

public enum Role {
  ROLE_ADMIN("ROLE_ADMIN"),
  ROLE_USER("ROLE_USER");

  public final String role;

  Role(String role) {
    this.role = role;
  }
}
