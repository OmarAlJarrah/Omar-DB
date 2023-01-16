package com.omar.model.exception;

import com.omar.constant.Message;

public class DataCollectionNotFoundException extends Exception {
  public DataCollectionNotFoundException() {
    super(Message.TABLE_NOT_FOUND.message);
  }
}
