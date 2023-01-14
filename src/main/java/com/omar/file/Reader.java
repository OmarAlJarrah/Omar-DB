package com.omar.file;

import com.omar.model.db.abstraction.Table;
import com.omar.model.db.impl.metadata.EmptyId;
import com.omar.model.db.impl.metadata.Id;
import com.omar.model.exception.RecordNotFoundException;
import com.omar.model.exception.TableNotFoundException;
import com.omar.util.abstraction.Parser;
import com.omar.util.abstraction.RecordPathBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Component
public class Reader {
  @Autowired
  private RecordPathBuilder recordPathBuilder;
  @Autowired
  private Parser parser;


  public Object getJsonObject(Table table, Id id) {
    return getJsonObject(table.getName(), id);
  }

  public Object getJsonObject(String tableName, Id id) {
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
