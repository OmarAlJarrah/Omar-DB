package com.omar.constant;

public enum KafkaTopics {
  CREATE("CREATE"),
  UPDATE("UPDATE"),
  DELETE("DELETE"),
  UNDO_DELETE("UNDO-DELETE"),
  UNDO_CREATE("UNDO-CREATE"),
  UNDO_UPDATE("UNDO-UPDATE");

  public final String topic;

  KafkaTopics(String topic) {
    this.topic = topic;
  }
}
