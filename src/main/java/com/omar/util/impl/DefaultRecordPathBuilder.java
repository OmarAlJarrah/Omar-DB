package com.omar.util.impl;

import com.omar.constant.Constant;
import com.omar.model.db.abstraction.Table;
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
  public String buildPathString(Table table, Id id) {
    return buildPathString(table.getName(), id);
  }

  @Override
  public String buildPathString(String tableName, Id id) {
    return new StringJoiner("/")
        .add(Paths.get("").toAbsolutePath().toString())
        .add(Constant.DB.toString())
        .add(tableName)
        .add(id.toString())
        .toString();
  }
}
