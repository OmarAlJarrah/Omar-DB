package com.omar.model.database;


import lombok.Builder;
import java.util.List;

@Builder
public class DatabaseMetadata {
  Id id;
  String databaseName;
  List<Id> nodesIds;
  Long nodesCount;
  List<NodeMetadata> nodes;
  List<SchemaMetadata> schemas;
}
