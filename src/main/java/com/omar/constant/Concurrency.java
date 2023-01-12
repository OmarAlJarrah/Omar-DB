package com.omar.constant;

public enum Concurrency {
  RECORD_POOL_LIMIT((int) 1e6);

  public Integer value;

  Concurrency(int value) {
    this.value = value;
  }
}
