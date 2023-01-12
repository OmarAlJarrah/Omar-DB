package com.omar.model.exception;

import com.omar.constant.Message;

public class RecordNotFoundException extends Exception {
  public RecordNotFoundException() {
    super(Message.RECORD_NOT_FOUND.message);
  }
}
