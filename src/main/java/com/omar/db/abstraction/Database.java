package com.omar.db.abstraction;

import com.omar.model.data.filter.abstraction.Filter;
import com.omar.model.db.abstraction.Table;

import java.util.List;
import java.util.Optional;

public interface Database {
  boolean write(Object data);
  void fromSchema(Object schema);
  Optional<List<Object>> readBy(Object filer);
  Optional<List<Object>> readByAllOf(List<Filter> filters);
  Optional<List<Object>> readAnyOf(List<Filter> filters);
  Optional<Object> readById(Long id);

  List<Table> getTables();
}
