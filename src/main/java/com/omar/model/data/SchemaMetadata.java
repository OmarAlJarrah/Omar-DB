package com.omar.model.data;

import com.omar.model.database.implementation.metadata.Id;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public class SchemaMetadata {
  private Id id;
  private List<String> indices;
  private Map<String, Object> columns;




}
