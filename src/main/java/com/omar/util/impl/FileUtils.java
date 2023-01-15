package com.omar.util.impl;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

@Component
public class FileUtils {
  public static void createFileIfAbsent(String filePath) {
    File file;
    try {
      file = new File(filePath);
      if (!file.exists()) {
        file.createNewFile();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void createDirectoryIfAbsent(String filePath) {
    File file = new File(filePath);
    if (!file.exists()) {
      file.mkdir();
    }
  }

  public static JSONObject readJsonFile(File file) {
    StringJoiner jsonString = new StringJoiner("\n");
    try (var reader = new BufferedReader(new FileReader(file))) {
      for (String line : reader.lines().toList()) {
        jsonString.add(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new JSONObject(jsonString.toString());
  }
}