package com.omar.model.event;

import com.omar.model.db.impl.metadata.Id;
import org.json.JSONObject;

import java.util.List;

public record UndoDeleteEvent(
    Id id,
    JSONObject previousState,
    String publisherNode,
    String collectionName,
    List<String> publishedTo) implements Event {
  @Override
  public String getPublisher() {
    return this.publisherNode;
  }

  @Override
  public List<String> getPublishedTo() {
    return publishedTo;
  }
  
  public static UndoDeleteEvent from(DeleteEvent event) {
    return new UndoDeleteEvent(event.id(),
        event.previousState(),
        event.publisherNode(),
        event.collectionName(),
        event.publishedTo()
    );
  }
}
