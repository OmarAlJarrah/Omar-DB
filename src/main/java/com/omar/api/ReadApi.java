package com.omar.api;

import com.omar.file.Reader;
import com.omar.model.db.impl.metadata.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/read")
public class ReadApi {
  @Autowired
  private Reader reader;
  
  @GetMapping("byId/{collection}/{id}")
  public ResponseEntity<String> readById(@PathVariable("collection") String collectionName, @PathVariable("id") String id){
    return new ResponseEntity<>(reader.getJsonObject(collectionName, new Id(UUID.fromString(id))).toString(), HttpStatus.OK);
  }

  @GetMapping("/{collection}/all")
  public ResponseEntity<String> readAll(@PathVariable("collection") String collectionName) {
    return new ResponseEntity<>(reader.getAllJsonObjects(collectionName).toString(), HttpStatus.OK);
  }
}
