package com.omar.model.database.abstraction;

import java.util.List;
import java.util.Optional;

public interface Transaction {
  Optional<Object> implement();
}
