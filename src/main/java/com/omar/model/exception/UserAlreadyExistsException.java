package com.omar.model.exception;

import com.omar.constant.Message;

public class UserAlreadyExistsException extends Exception {
  public UserAlreadyExistsException() {
    super(Message.USER_ALREADY_EXISTS.message);
  }
}
