package com.omar.constant;

public enum Message {
  TABLE_NOT_FOUND("Table not found!"),
  RECORD_NOT_FOUND("Record not found!");

  public String message;

  Message(String message) {
    this.message = message;
  }
}
