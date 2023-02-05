package com.omar.api;

import com.omar.file.ConcurrentWriter;
import com.omar.model.db.impl.metadata.Id;
import com.omar.service.P2PEventPublisherService;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/write")
public class WriteApi {
  private final ConcurrentWriter writer;

  private final P2PEventPublisherService eventPublisherService;

  public WriteApi(ConcurrentWriter writer, P2PEventPublisherService eventPublisherService) {
    this.writer = writer;
    this.eventPublisherService = eventPublisherService;
  }

  @PostMapping("{collection}/{id}")
  public ResponseEntity<String> write(@PathVariable("collection") String collectionName,
                                      @PathVariable("id") String id,
                                      @RequestBody String object) {

    try {
      writer.write(Id.fromString(id), new JSONObject(object), collectionName);
      eventPublisherService.publishWriteEvent(collectionName, id, new JSONObject(object));
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
