package com.omar.model.db.impl.transaction;

import com.omar.model.db.abstraction.Transaction;

import java.util.List;
import java.util.Optional;

public class TransactionImpl implements Transaction {
  protected List<Object> affectedRows;
  protected List<Object> updatedRows;

  @Override
  public Optional<Object> commit() {
    return Optional.empty();
  }

  @Override
  public Optional<Object> revert() {
    return Optional.empty();
  }
}
