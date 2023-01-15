package com.omar.util.abstraction;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public interface Parser {
  Object parse(File file);
}
