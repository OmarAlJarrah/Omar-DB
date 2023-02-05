package com.omar.model.db.abstraction;

import java.util.Optional;

public interface Transaction {
  Optional<Object> commit();

  Optional<Object> revert();
}
