package com.omar.query.impl;

import com.omar.query.abstraction.Processor;

import java.util.List;
import java.util.Optional;

public class JsonQueryProcessor implements Processor {

  private JsonQueryProcessor() {}

  public static JsonQueryProcessor newInstance() {
    return new JsonQueryProcessor();
  }

  @Override
  public List<Object> processReadQuery(String query) {
    return null;
  }

  @Override
  public Optional<String> processWriteQuery(String query) {
    return Optional.empty();
  }
}
