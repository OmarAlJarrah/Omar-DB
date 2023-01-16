package com.omar.util.impl;

import com.omar.constant.FileExtension;
import com.omar.model.db.abstraction.DataCollection;
import com.omar.model.db.impl.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import com.omar.util.abstraction.Writer;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

@Component
@Primary
public class JsonWriter implements Writer {
  @Autowired
  private RecordPathBuilder recordPathBuilder = new DefaultRecordPathBuilder();

  @Override
  public void write(Id id, JSONObject jsonObject, DataCollection collection) {
    write(id, jsonObject, collection.getName());
  }

  @Override
  public void write(Id id, JSONObject object, String collectionName) {
    writeJsonObjectToFiles(recordPathBuilder.buildRecordPathBuilder(collectionName ,id.toString()), object);
  }

  private void writeJsonObjectToFiles(String currentPath, JSONObject object) {
    FileUtils.createDirectoryIfAbsent(currentPath);

    Iterator<String> keys = object.keys();
    while (keys.hasNext()) {
      String key = keys.next();
      Object value = object.get(key);

      if (value instanceof JSONObject) {
        String nextPath = (currentPath.endsWith("/") ? currentPath : currentPath + File.separator) + key;
        FileUtils.createDirectoryIfAbsent(nextPath);

        writeJsonObjectToFiles(nextPath, object.getJSONObject(key));
        continue;
      }

      String filePath = ((currentPath.endsWith("/") ? currentPath : currentPath + File.separator) + key);
      filePath = filePath.endsWith(".json") ? filePath : filePath + FileExtension.JSON.value;

      FileUtils.createFileIfAbsent(filePath);

      try (var writer = new BufferedWriter(new FileWriter(filePath))) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(key, object.get(key));
        writer.write(jsonObject.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void delete(File file) {
    try {
      org.apache.commons.io.FileUtils.deleteDirectory(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
