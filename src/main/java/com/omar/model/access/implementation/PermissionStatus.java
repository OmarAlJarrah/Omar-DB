package com.omar.model.access.implementation;

public enum PermissionStatus {
  ALLOWED(true),
  DENIED(false);

  public final boolean value;

  PermissionStatus(boolean value) {
    this.value = value;
  }
}
