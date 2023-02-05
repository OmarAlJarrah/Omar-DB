package com.omar.service;

import com.omar.file.ConcurrentWriter;
import com.omar.model.event.CreateEvent;
import com.omar.model.event.DeleteEvent;
import com.omar.model.event.Event;
import com.omar.model.event.UpdateEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CommitService {
  @Value("${nodeId}")
  private String nodeId;

  private final ConcurrentWriter writer;

  public CommitService(ConcurrentWriter writer) {
    this.writer = writer;
  }

  public boolean commit(Event event) {
    try {
      // As we are in a separate thread from the main thread, thanks to the
      // ConcurrentKafkaListenerContainerFactory, we can sleep the current thread,
      // and so other threads will be picked. This ensures eventual consistency.
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
      return false;
    }

    boolean unintendedMessage = event.getPublisher().equalsIgnoreCase(nodeId);

    for (String publishedTo: event.getPublishedTo()) {
      unintendedMessage = unintendedMessage || !publishedTo.equalsIgnoreCase(nodeId);
    }
    
    if (unintendedMessage) {
      return true;
    }

    if (event instanceof CreateEvent) {
      return commitCreate((CreateEvent) event);
    }

    if (event instanceof UpdateEvent) {
      return commitUpdate((UpdateEvent) event);
    }

    if (event instanceof DeleteEvent) {
      return commitDelete((DeleteEvent) event);
    }
    
    return false;
  }

  private boolean commitCreate(CreateEvent event) {
    try {
      writer.write(
          event.id(),
          event.newState(),
          event.collectionName()
      );
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  private boolean commitUpdate(UpdateEvent event) {
    try {
      writer.write(
          event.id(),
          event.newState(),
          event.collectionName()
      );
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  private boolean commitDelete(DeleteEvent event) {
    try {
      writer.delete(event.collectionName(), event.id().toString());
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
