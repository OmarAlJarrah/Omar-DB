package com.omar.model.db.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NodesWrapper {
  public Map<String, Master> masters = new ConcurrentHashMap<>();
}