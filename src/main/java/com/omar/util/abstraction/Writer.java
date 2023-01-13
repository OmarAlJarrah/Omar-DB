package com.omar.util.abstraction;

import com.omar.model.db.abstraction.Table;
import com.omar.model.db.impl.metadata.Id;
import org.json.JSONObject;

public interface Writer {
  void write(Id id, JSONObject jsonObject, Table table);
  void write(Id id, JSONObject jsonObject, String tableName);
}
