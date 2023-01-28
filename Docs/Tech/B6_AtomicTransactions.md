# Atomic Transactions
## Overview 
Atomic transactions, also known as atomic operations, are a fundamental concept in database management systems. They ensure that a group of database operations are executed as a single unit, and that either all of the operations are completed successfully or none of them are. This is important for maintaining the integrity and consistency of the database, and for ensuring that data is not lost or corrupted during a transaction.

### How Atomic Transactions Work?
Atomic transactions work by using a technique called two-phase commit (2PC). The 2PC protocol is used to coordinate the actions of multiple database systems, or "participants," involved in a transaction. The 2PC protocol has two phases: the prepare phase and the commit phase.

During the prepare phase, each participant in the transaction will check if it is able to commit the transaction. If any participant is unable to commit, the transaction is rolled back and all changes are discarded. If all participants are able to commit, the transaction moves on to the commit phase.

During the commit phase, each participant will commit the transaction, making the changes permanent. If any participant is unable to commit the transaction, the entire transaction is rolled back and all changes are discarded. This ensures that the database remains in a consistent state, even if one participant is unable to commit.

### Advantages of Atomic Transactions
Atomic transactions provide several advantages for database management systems, including:

+ **Data consistency**: Atomic transactions ensure that the database remains in a consistent state, even if one participant is unable to commit the transaction. This prevents data from becoming corrupted or lost during a transaction.

+ **Isolation**: Atomic transactions are isolated from other transactions, meaning that changes made during a transaction are not visible to other transactions until the transaction is committed. This prevents data from being read or modified by other transactions until the current transaction is complete.

+ **Durability**: Atomic transactions are durable, meaning that changes made during a transaction are permanent. This ensures that data is not lost in the event of a system failure.

Atomic transactions are a fundamental concept in database management systems, and provide several advantages for maintaining the integrity and consistency of the database. They ensure that a group of database operations are executed as a single unit, and that either all of the operations are completed successfully or none of them are. This helps prevent data from becoming corrupted or lost during a transaction, and ensures that the database remains in a consistent state.

### Implementation
I have ensured atomic transactions by saving the data before any change is done, then attempt to commit the changes, if any operation fails, the whole transaction will be undone by publishing a new write event to the old data.