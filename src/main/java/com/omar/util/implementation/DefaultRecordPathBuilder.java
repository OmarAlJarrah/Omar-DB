package com.omar.util.implementation;

import com.omar.constant.Constant;
import com.omar.model.database.abstraction.Table;
import com.omar.model.database.implementation.metadata.Id;
import com.omar.util.abstraction.RecordPathBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
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
