package com.omar.model.database.abstraction;

import com.omar.model.data.filter.abstraction.Filter;
import com.omar.model.database.implementation.Schema;

import java.util.List;
import java.util.Optional;

public interface Table {
  Optional<Object> findBy(List<Filter> filters);
  Table fromSchema(Schema schema);
}
