package com.omar.api;

import com.google.gson.Gson;
import com.omar.constant.KafkaTopics;
import com.omar.file.ConcurrentWriter;
import com.omar.file.Reader;
import com.omar.model.db.impl.metadata.Id;
import com.omar.model.event.DeleteEvent;
import com.omar.service.P2PEventPublisherService;
import com.omar.service.RandomNodeService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/delete")
public class DeleteApi {
  private final ConcurrentWriter writer;

  private final P2PEventPublisherService eventPublisherService;

  @Value("nodeId")
  private String nodeId;

  public DeleteApi(ConcurrentWriter concurrentWriter,
                   P2PEventPublisherService eventPublisherService) {
    writer = concurrentWriter;
    this.eventPublisherService = eventPublisherService;
  }

  @PostMapping("{collection}/{id}")
  public ResponseEntity<String> delete(@PathVariable("collection") String collectionName, @PathVariable("id") String id) {
    try {
      writer.delete(collectionName, id);

      eventPublisherService.publishDeleteEvent(
          collectionName,
          id
      );
    } catch (IOException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
