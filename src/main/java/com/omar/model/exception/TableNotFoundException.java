package com.omar.model.exception;

import com.omar.constant.Message;

public class TableNotFoundException extends Exception {
  public TableNotFoundException() {
    super(Message.TABLE_NOT_FOUND.message);
  }
}
