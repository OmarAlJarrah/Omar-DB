package com.omar.model.access.impl;

public enum PermissionStatus {
  ALLOWED(true),
  DENIED(false);

  public final boolean value;

  PermissionStatus(boolean value) {
    this.value = value;
  }
}
