package com.omar.util.impl;

import com.omar.constant.CollectionConstants;
import com.omar.constant.PathConstants;
import com.omar.model.db.abstraction.DataCollection;
import com.omar.model.db.impl.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.StringJoiner;

@Component
@Primary
public class DefaultRecordPathBuilder implements RecordPathBuilder {

  @Override
  public String buildPathString(DataCollection collection, Id id) {
    return buildPathString(collection.getName(), id);
  }

  @Override
  public String buildPathString(String collectionName, Id id) {
    return new StringJoiner("/")
        .add(Paths.get("").toAbsolutePath().toString())
        .add(CollectionConstants.DB.toString())
        .add(collectionName)
        .add(id.toString())
        .toString();
  }
  
  public String buildPathString(String collectionName) {
    return new StringJoiner("/")
        .add(Paths.get("").toAbsolutePath().toString())
        .add(CollectionConstants.DB.toString())
        .add(collectionName)
        .toString();
  }

  public String buildRecordPathBuilder(String collection, String id) {
    return new StringJoiner("/")
        .add(buildCollectionPathString(collection))
        .add(id)
        .toString();
  }

  public String buildCollectionPathString(String collection) {
    return new StringJoiner("/")
        .add(Paths.get("").toAbsolutePath().toString())
        .add(PathConstants.TABLES_PATH.path)
        .add(collection)
        .toString();
  }

  public String buildUserPathString(String username) {
    return new StringJoiner("/")
        .add(buildPathString(CollectionConstants.USERS.name()))
        .add(username)
        .toString();
  }
}
