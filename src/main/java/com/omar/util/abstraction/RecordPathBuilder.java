package com.omar.util.abstraction;

import com.omar.constant.PathConstants;
import com.omar.model.db.abstraction.DataCollection;
import com.omar.model.db.impl.metadata.Id;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.StringJoiner;

@Component
public interface RecordPathBuilder {
  String buildPathString(DataCollection collection, Id id);

  String buildPathString(String collectionName, Id id);

  String buildPathString(String collectionName);

  String buildRecordPathBuilder(String collection, String id);

  String buildCollectionPathString(String collection);

  String buildUserPathString(String username);
}
