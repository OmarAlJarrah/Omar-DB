package com.omar.model.db.impl.transaction;


import java.util.Optional;

public class DeleteTransaction extends TransactionImpl {
  @Override
  public Optional<Object> implement() {
    return Optional.empty();
  }
}
