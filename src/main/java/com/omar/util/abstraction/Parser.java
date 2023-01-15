package com.omar.util.abstraction;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public interface Parser {
  Object parse(File file);
}
