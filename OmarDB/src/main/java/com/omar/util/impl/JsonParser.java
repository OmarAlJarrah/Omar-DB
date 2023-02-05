package com.omar.util.impl;

import com.omar.util.abstraction.Parser;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

@Component
@Primary
public class JsonParser implements Parser {
  @Override
  public Object parse(File file) {
    JSONObject result = new JSONObject();

    if (!file.exists()) {
      result.put(file.getName(), new JSONObject());
      return result;
    }

    if (file.isDirectory()) {
      JSONObject childJsonObject = new JSONObject();
      for (String child : Objects.requireNonNull(file.list())) {
        String childFilePath = new StringJoiner("/")
            .add(file.getAbsolutePath())
            .add(child)
            .toString();

        File childFile = new File(childFilePath);
        JSONObject currentJsonObject = (JSONObject) parse(childFile);
        Iterator<String> keys = currentJsonObject.keys();

        while (keys.hasNext()) {
          String key = keys.next();
          childJsonObject.put(key, currentJsonObject.get(key));
        }
      }

      result.put(FilenameUtils.getBaseName(file.getName()), childJsonObject);

    } else {
      result = FileUtils.readJsonFile(file);
    }

    return result;
  }

  public static void main(String[] args) {
//    Parser parser = new JsonParser();
//    var res = parser.parse(new File("/Users/oaljarrah/IdeaProjects/Omar-DB/DB/collection/7954bc95-46e3s-4d5f-8cf1-12482e618cc5"));
//    System.out.println(res.toString());

  }
}
