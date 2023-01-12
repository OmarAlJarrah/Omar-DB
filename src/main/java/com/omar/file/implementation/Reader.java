package com.omar.file.implementation;

import com.omar.constant.Constant;
import com.omar.model.database.abstraction.Table;
import com.omar.model.database.implementation.metadata.EmptyId;
import com.omar.model.database.implementation.metadata.Id;
import com.omar.model.exception.RecordNotFoundException;
import com.omar.model.exception.TableNotFoundException;
import com.omar.util.abstraction.Parser;
import com.omar.util.abstraction.RecordPathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;


@Component
public class Reader {
  @Autowired private RecordPathBuilder recordPathBuilder;
  @Autowired private Parser parser;

  private void handleMissingData(String tableName, Id id) throws TableNotFoundException, RecordNotFoundException {
    File tablePath = new File(
        recordPathBuilder.buildPathString(
            tableName,
            new EmptyId(UUID.fromString(id.toString()))
        )
    );

    File recordPath = new File(recordPathBuilder.buildPathString(tableName, id));

    if (!tablePath.exists() || !tablePath.isDirectory()) {
      throw new TableNotFoundException();
    }

    if (!recordPath.exists() || !recordPath.isDirectory()) {
      throw new RecordNotFoundException();
    }
  }

  public Object getJsonObject(Table table, Id id) throws RecordNotFoundException, TableNotFoundException {
    return getJsonObject(table.getName(), id);
  }

  public Object getJsonObject(String tableName, Id id) throws RecordNotFoundException, TableNotFoundException {
    handleMissingData(tableName, id);

    return parser.parse(new File(
        recordPathBuilder.buildPathString(tableName, id)
    ));
  }

  public static void main(String[] args) throws IOException {

//    System.out.println(UUID.randomUUID());
//    System.out.println(new Reader().read("table", new Id(UUID.fromString("7954bc95-46e3-4d5f-8cf1-12482e618cc5"))));
//    new Reader().something();
  }
}
