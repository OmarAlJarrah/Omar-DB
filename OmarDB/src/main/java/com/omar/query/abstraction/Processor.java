package com.omar.query.abstraction;

import java.util.List;
import java.util.Optional;

public interface Processor {
  List<Object> processReadQuery(String query);
  Optional<String> processWriteQuery(String query);
}
