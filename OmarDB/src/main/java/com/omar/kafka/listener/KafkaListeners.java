package com.omar.kafka.listener;

import com.google.gson.Gson;
import com.omar.constant.KafkaTopics;
import com.omar.model.event.*;
import com.omar.service.CommitService;
import com.omar.service.UncommitService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
  private final Gson gson;
  private final CommitService commitService;
  private final UncommitService uncommitService;
  private final KafkaTemplate<String, String> kafkaTemplate;

  public KafkaListeners(Gson gson,
                        CommitService commitService,
                        UncommitService uncommitService,
                        KafkaTemplate<String, String> kafkaTemplate) {
    this.gson = gson;
    this.commitService = commitService;
    this.uncommitService = uncommitService;
    this.kafkaTemplate = kafkaTemplate;
  }

  @KafkaListener(topics = "UPDATE", groupId = "com.omar")
  public void updateListener(String message) {
    UpdateEvent event = gson.fromJson(message, UpdateEvent.class);

    if (commitService.commit(event)) {
      return;
    }

    UndoUpdateEvent undoUpdateEvent = UndoUpdateEvent.from(event);
    String messageToPublish = gson.toJson(undoUpdateEvent);
    kafkaTemplate.send(KafkaTopics.UNDO_UPDATE.topic, messageToPublish);
  }

  @KafkaListener(topics = "UNDO-DELETE", groupId = "com.omar")
  public void undoDeleteListener(String message) {
    UndoDeleteEvent event = gson.fromJson(message, UndoDeleteEvent.class);
    uncommitService.uncommit(event);
  }

  @KafkaListener(topics = "UNDO-CREATE", groupId = "com.omar")
  public void undoCreateListener(String message) {
    UndoCreateEvent event = gson.fromJson(message, UndoCreateEvent.class);
    uncommitService.uncommit(event);
  }

  @KafkaListener(topics = "UNDO-UPDATE", groupId = "com.omar")
  public void undoUpdateListener(String message) {
    UndoUpdateEvent event = gson.fromJson(message, UndoUpdateEvent.class);
    uncommitService.uncommit(event);
  }

  @KafkaListener(topics = "DELETE", groupId = "com.omar")
  public void deleteListener(String message) {
    DeleteEvent event = gson.fromJson(message, DeleteEvent.class);

    if (commitService.commit(event)) {
      return;
    }

    UndoDeleteEvent undoDeleteEvent = UndoDeleteEvent.from(event);
    String messageToPublish = gson.toJson(undoDeleteEvent);
    kafkaTemplate.send(KafkaTopics.UNDO_DELETE.topic, messageToPublish);
  }

  @KafkaListener(topics = "CREATE", groupId = "com.omar")
  public void createListener(String message) {
    CreateEvent event = gson.fromJson(message, CreateEvent.class);

    if (commitService.commit(event)) {
      return;
    }

    UndoCreateEvent undoCreateEven = UndoCreateEvent.from(event);
    String messageToPublish = gson.toJson(undoCreateEven);
    kafkaTemplate.send(KafkaTopics.UNDO_CREATE.topic, messageToPublish);
  }
}
