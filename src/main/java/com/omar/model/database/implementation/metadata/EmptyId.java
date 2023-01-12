package com.omar.model.database.implementation.metadata;

import java.util.UUID;

public class EmptyId extends Id {
  public EmptyId(UUID uuid) {
    super(uuid);
  }

  @Override
  public String toString() {
    return "";
  }
}
