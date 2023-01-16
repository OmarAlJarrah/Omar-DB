package com.omar.file;

import com.omar.constant.CollectionConstants;
import com.omar.constant.Concurrency;
import com.omar.model.db.impl.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import com.omar.util.impl.FileUtils;
import com.omar.util.impl.JsonWriter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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

  public void write(Id id, JSONObject jsonObject, String collectionName) throws IOException {
    File file = new File(recordPathBuilder.buildRecordPathString(collectionName, id.toString()));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      jsonWriter.write(id, jsonObject, collectionName);
    }
  }

  public void writeUser(JSONObject user) throws IOException {
    Id id = new Id(UUID.fromString(user.getString("username")));
    user.put("id", id.toString());
    File file = new File(recordPathBuilder.buildUserPathString(id.toString()));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      jsonWriter.write(id, user, CollectionConstants.USERS.name());
    }
  }

  public void deleteUser(String username) throws IOException {
    jsonWriter.delete(new File(recordPathBuilder.buildUserPathString(username)));
  }

  public void delete(String collectionName, String id) throws IOException {
    jsonWriter.delete(new File(recordPathBuilder.buildRecordPathString(collectionName, id)));
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

  public static void main(String[] args) throws IOException {
    ConcurrentWriter writer = new ConcurrentWriter();
    writer.write(new Id(UUID.randomUUID()),
        FileUtils.readJsonFile(new File("/Users/oaljarrah/IdeaProjects/Omar-DB/DB/USERS/query.json")),
        "CollectionX"
        );
  }
}
