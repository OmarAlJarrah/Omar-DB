package com.omar.util.abstraction;

import org.json.JSONObject;

import java.io.File;

public interface PartialParser {
  Object partialParse(File file, JSONObject query);
}
