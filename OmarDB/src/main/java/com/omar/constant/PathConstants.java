package com.omar.constant;

import java.util.StringJoiner;

public enum PathConstants {
  TABLES_PATH(new StringJoiner("/")
      .add(CollectionConstants.DB.name())
      .add(CollectionConstants.TABLES.name())
      .toString());


  public final String path;

  PathConstants(String value) {
    this.path = value;
  }
}
