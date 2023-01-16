package com.omar.api;

import com.omar.file.ConcurrentWriter;
import com.omar.model.db.impl.metadata.Id;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/write")
public class WriteApi {
  @Autowired
  private ConcurrentWriter writer;

  @PostMapping("{collection}/{id}")
  public ResponseEntity<String> write(@PathVariable("collection") String collectionName,
                                      @PathVariable("id") String id,
                                      @RequestBody Object object) {

    try {
      writer.write(Id.fromString(id), new JSONObject(object), collectionName);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(HttpStatus.ACCEPTED);
  }
}
