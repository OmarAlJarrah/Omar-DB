package com.omar.model.db.impl.metadata;

import java.util.UUID;

public class Id {
  private final UUID value;

  public Id(UUID uuid) {
    value = uuid;
  }
  @Override
  public String toString() {
    return value.toString();
  }

  public static Id fromString(String value) {
    return new Id(UUID.fromString(value));
  }
}

