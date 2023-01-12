package com.omar.util.abstraction;

import com.omar.model.database.abstraction.Table;
import com.omar.model.database.implementation.metadata.Id;

public interface RecordPathBuilder {
  String buildPathString(Table table, Id id);

  String buildPathString(String tableName, Id id);
}
