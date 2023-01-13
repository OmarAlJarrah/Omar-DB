package com.omar.util.impl;

import com.omar.util.abstraction.Parser;
import org.json.JSONObject;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

@Component
@Primary
public class JsonParser implements Parser {
  @Override
  public Object parse(File file) {
    JSONObject result = new JSONObject();

    if (file.isDirectory()) {
      JSONObject childJsonObject = new JSONObject();
      for (String child : Objects.requireNonNull(file.list())) {
        String childFilePath = new StringJoiner("/")
            .add(file.getAbsolutePath())
            .add(child)
            .toString();

        File childFile = new File(childFilePath);
        JSONObject currentJsonObject = (JSONObject) parse(childFile.getAbsoluteFile());
        Iterator<String> keys = currentJsonObject.keys();

        while (keys.hasNext()) {
          String key = keys.next();
          childJsonObject.put(key, currentJsonObject.get(key));
        }
      }

      result.put(file.getName(), childJsonObject);

    } else {
      StringJoiner jsonString = new StringJoiner("\n");
      try (var reader = new BufferedReader(new FileReader(file))) {
        for (String line : reader.lines().toList()) {
          jsonString.add(line);
        }

        result = new JSONObject(jsonString.toString());

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    return result;
  }

  // TODO: implement the following function
//  public Object parsePartially()

  public static void main(String[] args) {
    Parser parser = new JsonParser();
    var res = parser.parse(new File("/Users/oaljarrah/IdeaProjects/Omar-DB/DB/table/7954bc95-46e3-4d5f-8cf1-12482e618cc5"));
    System.out.println(res.toString());
  }
}
