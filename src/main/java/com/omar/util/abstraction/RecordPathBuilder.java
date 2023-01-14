package com.omar.util.abstraction;

import com.omar.model.db.abstraction.Table;
import com.omar.model.db.impl.metadata.Id;
import org.springframework.stereotype.Component;

@Component
public interface RecordPathBuilder {
  String buildPathString(Table table, Id id);

  String buildPathString(String tableName, Id id);
}
