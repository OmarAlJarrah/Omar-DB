package com.omar.util.abstraction;

import java.io.File;
import java.io.FileNotFoundException;

public interface Parser {
  Object parse(File file);
}
