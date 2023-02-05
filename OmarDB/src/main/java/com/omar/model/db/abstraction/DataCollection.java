package com.omar.model.db.abstraction;

import com.omar.model.data.filter.abstraction.Filter;

import java.util.List;
import java.util.Optional;

public interface DataCollection {
  Optional<Object> findBy(List<Filter> filters);
  DataCollection fromSchema(Object schema);

  String getName();

  Long getCountOfRecords();
}
