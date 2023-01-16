package com.omar.file;

import com.omar.constant.Concurrency;
import com.omar.constant.CollectionConstants;
import com.omar.model.db.impl.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import com.omar.util.impl.DefaultRecordPathBuilder;
import com.omar.util.impl.FileUtils;
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
  @Autowired JsonWriter jsonWriter = new JsonWriter();
  @Autowired private RecordPathBuilder recordPathBuilder = new DefaultRecordPathBuilder();

  private final ConcurrentLinkedDeque<String> LRUManager = new ConcurrentLinkedDeque<>();
  private final Map<String, File> recordLockPool = new ConcurrentHashMap<>();

  public void write(Id id, JSONObject jsonObject, String collectionName) {
    File file = new File(recordPathBuilder.buildRecordPathBuilder(collectionName, id.toString()));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      jsonWriter.write(id, jsonObject, collectionName);
    }
  }

  public void writeUser(JSONObject user) {
    Id id = new Id(UUID.fromString(user.getString("username")));
    user.put("id", id.toString());
    File file = new File(recordPathBuilder.buildUserPathString(id.toString()));

    appendToPool(file);
    synchronized (recordLockPool.get(file.getAbsolutePath())) {
      jsonWriter.write(id, user, CollectionConstants.USERS.name());
    }
  }

  public void deleteUser(String username) {
    jsonWriter.delete(new File(recordPathBuilder.buildUserPathString(username)));
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

  public static void main(String[] args) {
    ConcurrentWriter writer = new ConcurrentWriter();
    writer.write(new Id(UUID.randomUUID()),
        FileUtils.readJsonFile(new File("/Users/oaljarrah/IdeaProjects/Omar-DB/DB/USERS/query.json")),
        "CollectionX"
        );
  }
}
