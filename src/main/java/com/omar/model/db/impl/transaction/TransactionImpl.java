package com.omar.model.db.impl.transaction;

import com.omar.model.db.abstraction.Transaction;

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
