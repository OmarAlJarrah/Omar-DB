package com.omar.file;

import com.omar.constant.Concurrency;
import com.omar.constant.Constant;
import com.omar.model.db.impl.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import com.omar.util.impl.JsonWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

@Component
public class ConcurrentWriter {
  @Autowired JsonWriter jsonWriter;
  @Autowired private RecordPathBuilder recordPathBuilder;

  private final ConcurrentLinkedDeque<String> LRUManager = new ConcurrentLinkedDeque<>();
  private final Map<String, File> recordLockPool = new ConcurrentHashMap<>();

  public void write(Id id, JSONObject jsonObject, String tableName) {
    File file = new File(recordPathBuilder.buildPathString(tableName, id));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      System.out.println(jsonObject);
      jsonWriter.write(id, jsonObject, tableName);
    }
  }

  public void writeUser(JSONObject user) {
    Id id = new Id(UUID.fromString(user.getString("username")));
    File file = new File(recordPathBuilder.buildPathString(String.valueOf(Constant.USERS),id));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      System.out.println(user);
      jsonWriter.write(id, user, Constant.USERS.name());
    }
  }

  private void appendToPool(File file) {
    String filePath = file.getAbsolutePath();
    boolean isAbsent = recordLockPool.putIfAbsent(filePath, file) == null;

    if (isAbsent) {
      LRUManager.addLast(filePath);
    }

    if (LRUManager.size() > Concurrency.RECORD_POOL_LIMIT.value) {
      String recordKeyToRemove = LRUManager.pollFirst();
      recordLockPool.remove(recordKeyToRemove);
    }
  }
}
