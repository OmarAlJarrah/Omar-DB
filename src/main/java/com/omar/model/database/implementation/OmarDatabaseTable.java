package com.omar.model.database.implementation;

import com.omar.model.data.filter.abstraction.Filter;
import com.omar.model.database.abstraction.Table;

import java.util.List;
import java.util.Optional;

public class OmarDatabaseTable implements Table {
  private Object schema;

  public Optional<Object> findBy(List<Filter> filters){
    return Optional.empty();
  }

  @Override
  public Table fromSchema(Schema schema) {
    return null;
  }
}
