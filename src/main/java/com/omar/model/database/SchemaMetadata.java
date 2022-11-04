package com.omar.model.database;

import lombok.Builder;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Builder
public class SchemaMetadata {
  private Id id;
  private List<String> indices;
  private Map<String, Optional<Object>> columns;
}
