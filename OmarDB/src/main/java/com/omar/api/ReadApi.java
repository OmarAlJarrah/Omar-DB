package com.omar.api;

import com.omar.file.Reader;
import com.omar.model.db.impl.metadata.Id;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/read")
public class ReadApi {
  private final Reader reader;

  public ReadApi(Reader reader) {
    this.reader = reader;
  }
  
  @GetMapping("{collection}/{id}")
  public ResponseEntity<String> read(@PathVariable("collection") String collectionName,
                                     @PathVariable("id") String id){
    return new ResponseEntity<>(reader.getJsonObject(collectionName, new Id(UUID.fromString(id))).toString(), HttpStatus.OK);
  }

  @GetMapping("/{collection}/all")
  public ResponseEntity<String> readAll(@PathVariable("collection") String collectionName) {
    return new ResponseEntity<>(reader.getAllJsonObjects(collectionName).toString(), HttpStatus.OK);
  }

  @GetMapping("/partial/{collection}/{id}")
  public ResponseEntity<String> read(@PathVariable("collection") String collectionName,
                                     @PathVariable("id") String id,
                                     @RequestBody String jsonQuery) {
    return new ResponseEntity<>(reader.getJsonObjectPartially(collectionName, Id.fromString(id),
        new JSONObject(jsonQuery)).toString(), HttpStatus.OK);
  }
}
