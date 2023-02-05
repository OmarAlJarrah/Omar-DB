# Low-Level Architecture: Services Interaction & Workflow

In this section, we will take an example on how a request gets committed to the database, and how components interact/ with each other. 

We will take a write operation as an example as it is usually thr the biggest concern in such applications.

### 1) Request passing the load balancer
Once a request passes the load balancer and actually hits a master node, the controller will catch that request, pass it to the `ConccurrentWriter` to commit it.
![image](../../assets/ConcurrentWriter.png)

### 2) Attempt to commit
The `ConccurrentWriter` will handle all concurrency concerns, once the environment is thread-safe, it will ask the `JSONWriter` to commit changes to memory.
![image](../../assets/FileUtils.png)

### 3) Request event published once committed
An event will be published to a kafka topic, such that other nodes can catch the same change.
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public EventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
```
EventPublisher class is a simple service that takes in a topic and message and sends the message to the specified topic using the kafkaTemplate instance.

You can then use the `EventPublisher` service to publish events to a topic in Apache Kafka.
