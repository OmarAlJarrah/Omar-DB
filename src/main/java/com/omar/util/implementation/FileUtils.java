package com.omar.util.implementation;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

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
}
