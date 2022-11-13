package com.omar.model.database.implementation.transaction;

import com.omar.model.database.abstraction.Transaction;

import java.util.List;
import java.util.Optional;

public class TransactionImpl implements Transaction {
  protected List<Object> affectedRows;
  protected List<Object> updatedRows;

  @Override
  public Optional<Object> implement() {
    return Optional.empty();
  }
}
