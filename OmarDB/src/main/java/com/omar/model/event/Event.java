package com.omar.model.event;

import java.util.List;

public interface Event {
  public String getPublisher();

  public List<String> getPublishedTo();
}
