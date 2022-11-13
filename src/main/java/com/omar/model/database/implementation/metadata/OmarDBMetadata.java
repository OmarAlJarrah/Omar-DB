package com.omar.model.database.implementation.metadata;


import com.omar.model.data.SchemaMetadata;
import lombok.Builder;
import java.util.List;

@Builder
public class OmarDBMetadata {
  Id id;
  String databaseName;
  List<Id> nodesIds;
  Long nodesCount;
  List<NodeMetadata> nodes;
  List<SchemaMetadata> schemas;
}
