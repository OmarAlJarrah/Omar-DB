package com.omar.util.abstraction;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PartialParser {
  Object partialParse(File file, JSONObject query);
}
