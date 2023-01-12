package com.omar.util.implementation;

import com.omar.constant.FileExtension;
import com.omar.model.database.abstraction.Table;
import com.omar.model.database.implementation.metadata.Id;
import com.omar.util.abstraction.Writer;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.UUID;

@Component
@Primary
public class JsonWriter implements Writer {

  @Override
  public void write(Id id, JSONObject jsonObject, Table table) {
    write(id, jsonObject, table.getName());
  }

  @Override
  public void write(Id id, JSONObject object, String tableName) {
    object = object.getJSONObject("7954bc95-46e3-4d5f-8cf1-12482e618cc5").getJSONObject("name");
    Iterator<String> keys = object.keys();
    System.out.println(object);

    while (keys.hasNext()) {
      String key = keys.next();
      System.out.println("KEY: " + key + ", Class: " + object.get(key).getClass());
    }
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
        writer.write(object.toString());
      } catch (IOException e) {
        System.out.println(filePath);
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    JsonParser parser = new JsonParser();
    JSONObject object = (JSONObject) parser.parse(
        new File("/Users/oaljarrah/IdeaProjects/Omar-DB/DB/table/7954bc95-46e3-4d5f-8cf1-12482e618cc5")
    );
    JsonWriter writer = new JsonWriter();
//    writer.write(new Id(UUID.fromString("7954bc95-46e3-4d5f-8cf1-12482e618cc5")), object, "table");
    writer.writeJsonObjectToFiles("/Users/oaljarrah/IdeaProjects/Omar-DB/DB/table/test", object);
    System.out.println(object);
  }
}
