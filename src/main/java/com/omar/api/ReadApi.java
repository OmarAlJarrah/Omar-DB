package com.omar.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/read")
public class ReadApi {
  @GetMapping("byId/{id}")
  public String readById(@PathVariable String id){
    return "Your ID is " + id + " ^^";
  }

  String instanceId = UUID.randomUUID().toString();

  @GetMapping("/test")
  public String hello() {
    return String.format("Hello from instance %s", instanceId);
  }
}
