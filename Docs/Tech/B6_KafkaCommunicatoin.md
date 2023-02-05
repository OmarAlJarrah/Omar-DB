# Kafka Communication
I have used Kafka to ensure P2P architecture, by having a kind of event driven architecture, in which events are shard among instances through Kafka's message queues.

## Event Types
Events are split into multiple types depending on the operation that has been committed, an event will be published only when a transaction has been fully committed on at least one node.

There many types of events, which all can be categorized into two main categories:
### 1) Do Change Events
In simple words, such event is published whenever a change is to be shared with other nodes, few examples of such event are:
+ `UpdateEvent`
+ `CreateEvent`
+ `DeleteEvent`

```java
// Publish an UpdateEvent
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
```

### 2) Undo Change Events
Such events are published when a node gets a message from another node, in which it was published to share the change that has happened in that node. When the later node catches the transaction, attempts to commit it, but it fails to achieve that, then it would publish a message asking other nodes to undo the change as one node failed to sync.

Of course this only applies to master nodes, as each master node will share the change with only one other master node, it would make sense to undo the commit, and publish an error message where the transaction gets saved into a separate kafka broker.

```java
private boolean uncommitUpdate(UndoUpdateEvent event) {
    try {
      writer.write(
          event.id(),
          event.previousState(),
          event.collectionName()
      );
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
}
```

If more than one node were picked to share the transaction, the change will at least live in one node, and developers later on can use event sourcing to sync the data among nodes.

To achieve the ability to fall back, the previous state of each transaction is saved and published as a part of the event itself.
```java
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
```
