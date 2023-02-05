package com.omar.service;

import com.omar.model.db.impl.NodesWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RandomNodeService {

  @Value("nodeId")
  private String nodeId;

  private Integer previousNodeIndex = 0;
  private final List<String> masters = new ArrayList<>();

  public RandomNodeService(NodesWrapper wrapper) {

    masters.addAll(wrapper.masters.keySet());
  }

  public String getOrderedMasterNode() {
    if (previousNodeIndex >= masters.size()) {
      previousNodeIndex = 0;
    }

    if (masters.size() == 1) {
      return masters.get(0);
    }

    if (masters.get(previousNodeIndex).equals(nodeId)) {
      this.previousNodeIndex++;
      return this.getOrderedMasterNode();
    }

    return masters.get(previousNodeIndex);
  }


}
