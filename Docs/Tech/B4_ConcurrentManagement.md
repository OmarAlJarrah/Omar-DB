# Concurrency Management
## Overview
### What is Concurrency?
**Concurrency** refers to the ability of a system or program to perform multiple tasks simultaneously or in parallel. It is the concept of having multiple execution threads or processes that are running independently and potentially interacting with each other. This can improve the overall performance and responsiveness of a system.

### What is Concurrency Management?
**Concurrency management** refers to the techniques and methods used to control and coordinate the execution of concurrent tasks in a system. It involves managing the access and modification of shared resources, preventing race conditions and deadlocks, and ensuring the correct order of execution of tasks. Concurrency management can be implemented through various techniques such as locks, semaphores, and transactional memory. It is an important aspect of multithreading and parallel computing, and helps to ensure the correct and stable operation of a system when multiple tasks are executing simultaneously.

### Why Concurrency?
Concurrency is used in databases to allow multiple users or processes to access and modify the database simultaneously without interfering with each other's work. Without concurrency control, multiple users or processes accessing the database at the same time could lead to data inconsistencies and conflicts. For example, two users may attempt to update the same record at the same time, leading to one user's changes being overwritten by the other.

Concurrency control mechanisms are used to ensure that multiple transactions can execute simultaneously without interfering with each other. They allow the database to maintain consistency, isolation, and durability of the data, even in a concurrent environment. This allows multiple users to read and write data concurrently while maintaining data integrity and consistency.

There are several techniques used in databases for concurrency management such as Locking, Optimistic concurrency control, and Pessimistic concurrency control.

## Operations Concurrency
All operations are concurrent & asynchronous, as operations can be categorized into 2 main types that are read and write, write operations are the the most crucial type when it comes to concurrency, due to the fact that it might result in inconsistency in data if not handled properly.

## Algorithm
I have developed a lock-based algorithm to handle concurrency management on write operations, inspired bu the LRU Cache idea, which consists of 2 main components:
+ LRU Files Pool Manager.
+ Resource Locking Pool.

The Algorithm goes as follows:
1) A write request is accepted, the ```write``` method accepts three params, the Id of the record, data wrapped in a ```JSONObject```, and the collection/table name.
```java
public void write(Id id, JSONObject jsonObject, String collectionName) throws IOException {
  // -- CODE --  
}
```

2) A file object is created to represent the directory inside the collection which will store all our data under.
```java
File file = new File(recordPathBuilder.buildRecordPathString(collectionName, id.toString()));
```

3) The file is then added to the pool if not present in first place, which is a ```ConcurrentHashMap```, which ensures thread safety, which contains a pair of ```key-value``` data, where the key is the absolute path. The ```appendToPool``` method will check if the file si already present in the pool or not, in case it is present it will do nothing, otherwise it will append the file to the tail of the ```LRUManager```, which is a ```ConcurrentLinkedDeque```, and it represents the LRU idea.
```java
String filePath = file.getAbsolutePath();
boolean isAbsent = recordLockPool.putIfAbsent(filePath, file) == null;

if (isAbsent) {
    LRUManager.addLast(filePath);
}
```

4) As the pool cannot have infinite size, it is limited by a fixed size I have picked based on the default Java limits, which goes up to around $10^6$. If the max size is violated, we will remove an item from the head of the LRU.
 ```java
if (LRUManager.size() > Concurrency.RECORD_POOL_LIMIT.value) {
    String recordKeyToRemove = LRUManager.pollFirst();
    recordLockPool.remove(recordKeyToRemove);
}
```

5) After the file has been added to the pool, now we will acquire the file from the pool, and have a lock over it. As the file is itself is not returned but rather a reference to the file is returned from the hash map, locking the file itself will be easy as we will always reference the same file.
```java
synchronized (recordLockPool.get(file.getAbsolutePath())) {
    // -- CODE --  
}
```

6) Finally, we will let the `JsonWriter` handlle the write operation, which has been described before in detail.
```java
jsonWriter.write(id, user, CollectionConstants.USERS.name());
```