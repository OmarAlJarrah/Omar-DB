package com.omar.model.db.impl.transaction;

import java.util.Optional;

public class UpdateTransaction extends TransactionImpl {
  @Override
  public Optional<Object> implement() {
    return Optional.empty();
  }
}
