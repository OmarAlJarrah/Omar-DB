package com.omar.service;

import com.omar.file.ConcurrentWriter;
import com.omar.model.event.Event;
import com.omar.model.event.UndoCreateEvent;
import com.omar.model.event.UndoDeleteEvent;
import com.omar.model.event.UndoUpdateEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UncommitService {
  @Value("${nodeId}")
  private String nodeId;

  private final ConcurrentWriter writer;

  public UncommitService(ConcurrentWriter writer) {
    this.writer = writer;
  }

  public boolean uncommit(Event event) {
    boolean unintendedMessage = event.getPublisher().equalsIgnoreCase(nodeId);

    for (String publishedTo : event.getPublishedTo()) {
      unintendedMessage = unintendedMessage && !publishedTo.equalsIgnoreCase(nodeId);
    }

    if (unintendedMessage) {
      return true;
    }

    if (event instanceof UndoDeleteEvent) {
      return uncommitDelete((UndoDeleteEvent) event);
    }

    if (event instanceof UndoCreateEvent) {
      return uncommitCreate((UndoCreateEvent) event);
    }

    if (event instanceof UndoUpdateEvent) {
      return uncommitUpdate((UndoUpdateEvent) event);
    }

    return false;
  }

  private boolean uncommitDelete(UndoDeleteEvent event) {
    try {
      writer.write(
          event.id(),
          event.previousState(),
          event.collectionName()
      );
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  private boolean uncommitCreate(UndoCreateEvent event) {
    try {
      writer.delete(
          event.collectionName(),
          event.id().toString()
      );
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  private boolean uncommitUpdate(UndoUpdateEvent event) {
    try {
      writer.write(
          event.id(),
          event.previousState(),
          event.collectionName()
      );
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }
}
