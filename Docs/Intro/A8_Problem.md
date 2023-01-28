# Problem Statement
## Description
Design a NoSQL Database, that focuses on achieving the following characteristics:

### 1) Atomicity
Atomic transactions refer to a series of database operations that are treated as a single, indivisible operation. This means that either all the operations are completed, or none of them are. 

Atomic transactions ensure that the database remains in a consistent state even in the event of a failure or error. They are often used to ensure data consistency and integrity in distributed systems.

### 2) Optimized Read Operations
Read operations take priority over write operations. Read operations should be executed immediately, meanwhile it's okay for write operations to take time to take effect.

### 3) High Availability, Eventual Consistency
Database should be available at all times. According to CAP theorem, we are sacrificing immediate consistency (real-time) data to achieve high availability. In other words, any operation shall be accepted and processes immediately, meanwhile write operations will take time to have an effect.

### 4) Read-Write Optimization on Filesystem level
Read and write operations should be optimized on filesystem level, which is usually missing on most of the popular document-based NoSQL databases.

### 5) Concurrency
Database should achieve thread-safety in all transactions, with minimum tradeoff on performance level.

### 6) Partition Tolerance
Database should continue to function when network partitions occur. According to CAP theorem, we are sacrificing immediate consistency (real-time) data to achieve partition tolerance.

### 7) Isolation
Concurrent transactions should not interfere with each other.

### 8) Durability
The results of a committed transaction will survive any subsequent failures.
