package com.omar.file.implementation;

import com.omar.constant.Concurrency;
import com.omar.model.database.implementation.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import com.omar.util.implementation.JsonWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentWriter {
  @Autowired JsonWriter jsonWriter;
  @Autowired private RecordPathBuilder recordPathBuilder;

  private final ConcurrentLinkedDeque<String> LRUManager = new ConcurrentLinkedDeque<>();
  private final Map<String, File> recordLockPool = new ConcurrentHashMap<>();

  private void write(Id id, JSONObject jsonObject, String tableName) {
    File file = new File(recordPathBuilder.buildPathString(tableName, id));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      jsonWriter.write(id, jsonObject, tableName);
    }
  }

  private void appendToPool(File file) {
    String filePath = file.getAbsolutePath();
    boolean wasAbsent = recordLockPool.putIfAbsent(filePath, file) == null;

    if (wasAbsent) {
      LRUManager.addLast(filePath);
    }

    if (LRUManager.size() > Concurrency.RECORD_POOL_LIMIT.value) {
      String recordKeyToRemove = LRUManager.pollFirst();
      recordLockPool.remove(recordKeyToRemove);
    }
  }
}
