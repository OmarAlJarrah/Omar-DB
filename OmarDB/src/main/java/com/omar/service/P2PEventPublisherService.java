package com.omar.service;


import com.google.gson.Gson;
import com.omar.constant.KafkaTopics;
import com.omar.file.Reader;
import com.omar.model.db.impl.metadata.Id;
import com.omar.model.event.CreateEvent;
import com.omar.model.event.DeleteEvent;
import com.omar.model.event.UpdateEvent;
import com.omar.util.abstraction.RecordPathBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class P2PEventPublisherService {
  @Value("${nodeId}")
  private String nodeId;

  private final RecordPathBuilder recordPathBuilder;
  private final KafkaTemplate<String, String> kafkaTemplate;

  private final Reader reader;

  private final Gson gson;


  private final RandomNodeService randomNodeService;

  public P2PEventPublisherService(RecordPathBuilder recordPathBuilder,
                                  KafkaTemplate<String, String> kafkaTemplate,
                                  Reader reader,
                                  Gson gson,
                                  RandomNodeService randomNodeService) {
    this.recordPathBuilder = recordPathBuilder;
    this.kafkaTemplate = kafkaTemplate;
    this.reader = reader;
    this.gson = gson;
    this.randomNodeService = randomNodeService;
  }

  private void publish(String message, String topic) {
    kafkaTemplate.send(topic, message);
  }

  public void publishDeleteEvent(String collectionName,
                                 String id) {
    DeleteEvent event = new DeleteEvent(
        Id.fromString(id),
        (JSONObject) reader.getJsonObject(collectionName, Id.fromString(id)),
        nodeId,
        collectionName,
        List.of(randomNodeService.getOrderedMasterNode())
    );

    publish(
        gson.toJson(event),
        KafkaTopics.DELETE.topic
    );
  }

  public void publishWriteEvent(String collectionName,
                                String id,
                                JSONObject object) {
    boolean isUpdateOperation = new File(recordPathBuilder.buildRecordPathString(collectionName, id)).exists();

    if (isUpdateOperation) {
      publishUpdateEvent(collectionName, id, new JSONObject(object));
    } else {
      publishCreateEvent(collectionName, id, new JSONObject(object));
    }
  }

  private void publishUpdateEvent(String collectionName,
                                  String id,
                                  JSONObject object) {
    UpdateEvent event = new UpdateEvent(
        Id.fromString(id),
        (JSONObject) reader.getJsonObject(collectionName, Id.fromString(id)),
        object,
        nodeId,
        collectionName,
        List.of(randomNodeService.getOrderedMasterNode())
    );

    String message = gson.toJson(event);
    kafkaTemplate.send(KafkaTopics.UPDATE.topic, message);
  }

  private void publishCreateEvent(String collectionName,
                                  String id,
                                  JSONObject object) {
    CreateEvent event = new CreateEvent(
        Id.fromString(id),
        object,
        nodeId,
        collectionName,
        List.of(randomNodeService.getOrderedMasterNode())
    );

    String message = gson.toJson(event);
    kafkaTemplate.send(KafkaTopics.CREATE.topic, message);
  }
}
