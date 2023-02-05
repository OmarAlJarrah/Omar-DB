package com.omar.file;

import com.omar.constant.PathConstants;
import com.omar.model.db.abstraction.DataCollection;
import com.omar.model.db.impl.metadata.Id;
import com.omar.util.abstraction.Parser;
import com.omar.util.abstraction.PartialParser;
import com.omar.util.abstraction.RecordPathBuilder;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;


@Component
public class Reader {
  private final RecordPathBuilder recordPathBuilder;
  private final Parser parser;
  private final PartialParser partialParser;

  public Reader(Parser parser, RecordPathBuilder recordPathBuilder, PartialParser partialParser) {
    this.parser = parser;
    this.recordPathBuilder = recordPathBuilder;
    this.partialParser = partialParser;
  }


  public Object getJsonObject(DataCollection collection, Id id) {
    return getJsonObject(collection.getName(), id);
  }

  public Object getJsonObject(String collectionName, Id id) {
    return parser.parse(
        new File(recordPathBuilder.buildRecordPathString(collectionName, id.toString())
    ));
  }

  public Object getJsonObjectPartially(String collectionName, Id id, JSONObject query) {
    return partialParser.partialParse(
        new File(recordPathBuilder.buildRecordPathString(collectionName, id.toString())), query
    );
  }

  public Object getUserJsonObject(String username) {
    return parser.parse(new File(
        recordPathBuilder.buildUserPathString(username)
    ));
  }
  
  public Object getAllJsonObjects(String collectionName) {
    JSONObject result = new JSONObject();
    File collectionDirectory = new File(recordPathBuilder.buildCollectionPathString(collectionName));

    boolean isEmptyDataCollection = !collectionDirectory.exists()
        || !collectionDirectory.isDirectory() 
        || collectionDirectory.list() == null
        || !collectionDirectory.getAbsolutePath().contains(PathConstants.TABLES_PATH.path)
        || Objects.requireNonNull(collectionDirectory.list()).length == 0;

    if (isEmptyDataCollection) {
      return result;
    }
    
    for (File file: Objects.requireNonNull(collectionDirectory.listFiles())) {
      String fileName = FilenameUtils.getBaseName(file.getName());
      result.put(fileName, getJsonObject(collectionName, new Id(UUID.fromString(fileName))));
    }
    return result;
  }
}
