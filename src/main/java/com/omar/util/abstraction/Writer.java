package com.omar.util.abstraction;

import com.omar.model.database.abstraction.Table;
import com.omar.model.database.implementation.metadata.Id;
import org.json.JSONObject;

public interface Writer {
  void write(Id id, JSONObject jsonObject, Table table);
  void write(Id id, JSONObject jsonObject, String tableName);
}
