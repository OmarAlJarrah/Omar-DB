package com.omar.util.impl;

import com.omar.util.abstraction.Parser;
import com.omar.util.abstraction.PartialParser;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class JsonPartialParser implements PartialParser {

  @Autowired
  private Parser parser = new JsonParser();

  public Object partialParse(File file, JSONObject query) {
    JSONObject result = new JSONObject();

    String fileName = FilenameUtils.getBaseName(file.getName());

    if (shouldUseParse(query, fileName)) {
      return parser.parse(file);
    }

    if (query.get(fileName) instanceof Boolean && !query.getBoolean(fileName)) {
      return result;
    }


      if (!file.exists() || query.isEmpty()) {
      return result;
    }

    if (file.isDirectory()) {
      JSONObject nextQuery = query.getJSONObject(fileName);
      JSONObject childJsonObject = new JSONObject();
      for (String child : Objects.requireNonNull(file.list())) {
        String childFilePath = new StringJoiner("/")
            .add(file.getAbsolutePath())
            .add(child)
            .toString();

        File childFile = new File(childFilePath);
        String childFileName = FilenameUtils.getBaseName(childFile.getName());
        JSONObject currentJsonObject;

        if (nextQuery.isNull(childFileName)) {
          continue;
        }
        if (shouldUseParse(nextQuery, childFileName)) {
          currentJsonObject = (JSONObject) parser.parse(childFile);
          childJsonObject.put(childFileName, currentJsonObject.get(childFileName));
          continue;
        } else {
          currentJsonObject = (JSONObject) partialParse(childFile.getAbsoluteFile(), query.getJSONObject(fileName));
        }

        Iterator<String> keys = currentJsonObject.keys();

        while (keys.hasNext()) {
          String key = keys.next();
          if (!nextQuery.isNull(key)) {
            childJsonObject.put(key, currentJsonObject.get(key));
          }
        }
      }

      result.put(fileName, childJsonObject);

    } else {
      result = FileUtils.readJsonFile(file);
    }
    return result;
  }

  private boolean shouldUseParse(JSONObject query, String currentKey) {
    return query.get(currentKey) instanceof Boolean && query.getBoolean(currentKey);
  }
}
