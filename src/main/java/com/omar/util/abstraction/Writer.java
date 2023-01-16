package com.omar.util.abstraction;

import com.omar.model.db.abstraction.DataCollection;
import com.omar.model.db.impl.metadata.Id;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public interface Writer {
  void write(Id id, JSONObject jsonObject, DataCollection collection);
  void write(Id id, JSONObject jsonObject, String collectionName);
}
