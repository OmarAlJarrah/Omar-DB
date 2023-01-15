package com.omar.model.access.impl;

public enum Role {
  ROLE_ADMIN("ADMIN"),
  ROLE_USER("USER");

  public final String role;

  Role(String role) {
    this.role = role;
  }
}
