# Database
## What as a Database?
In computing, a database is an organized collection of data stored and accessed electronically. Small databases can be stored on a file system, while large databases are hosted on computer clusters or cloud storage. The design of databases spans formal techniques and practical considerations, including data modeling, efficient data representation and storage, query languages, security and privacy of sensitive data, and distributed computing issues, including supporting concurrent access and fault tolerance.

## What is a DBMS?
A database management system (DBMS) is the software that interacts with end users, applications, and the database itself to capture and analyze the data. The DBMS software additionally encompasses the core facilities provided to administer the database. The sum total of the database, the DBMS and the associated applications can be referred to as a database system. Often the term "database" is also used loosely to refer to any of the DBMS, the database system or an application associated with the database.

## Terminology and overview
Computer scientists may classify database management systems according to the database models that they support. Relational databases became dominant in the 1980s. These model data as rows and columns in a series of tables, and the vast majority use SQL for writing and querying data. In the 2000s, non-relational databases became popular, collectively referred to as NoSQL, because they use different query languages.

Formally, a "database" refers to a set of related data and the way it is organized. Access to this data is usually provided by a "database management system" (DBMS) consisting of an integrated set of computer software that allows users to interact with one or more databases and provides access to all of the data contained in the database (although restrictions may exist that limit access to particular data). The DBMS provides various functions that allow entry, storage and retrieval of large quantities of information and provides ways to manage how that information is organized.

Because of the close relationship between them, the term "database" is often used casually to refer to both a database and the DBMS used to manipulate it.

Outside the world of professional information technology, the term database is often used to refer to any collection of related data (such as a spreadsheet or a card index) as size and usage requirements typically necessitate use of a database management system.

Existing DBMSs provide various functions that allow management of a database and its data which can be classified into four main functional groups:

+ Data definition – Creation, modification and removal of definitions that define the organization of the data.
+ Update – Insertion, modification, and deletion of the actual data.
+ Retrieval – Providing information in a form directly usable or for further processing by other applications. The retrieved data may be made available in a form basically the same as it is stored in the database or in a new form obtained by altering or combining existing data from the database.
+ Administration – Registering and monitoring users, enforcing data security, monitoring performance, maintaining data integrity, dealing with concurrency control, and recovering information that has been corrupted by some event such as an unexpected system failure.
+ Both a database and its DBMS conform to the principles of a particular database model. "Database system" refers collectively to the database model, database management system, and database.

Physically, database servers are dedicated computers that hold the actual databases and run only the DBMS and related software. Database servers are usually multiprocessor computers, with generous memory and RAID disk arrays used for stable storage. Hardware database accelerators, connected to one or more servers via a high-speed channel, are also used in large volume transaction processing environments. DBMSs are found at the heart of most database applications. DBMSs may be built around a custom multitasking kernel with built-in networking support, but modern DBMSs typically rely on a standard operating system to provide these functions.

Since DBMSs comprise a significant market, computer and storage vendors often take into account DBMS requirements in their own development plans.

Databases and DBMSs can be categorized according to the database model(s) that they support (such as relational or XML), the type(s) of computer they run on (from a server cluster to a mobile phone), the query language(s) used to access the database (such as SQL or XQuery), and their internal engineering, which affects performance, scalability, resilience, and security.

There are several different types of databases, each with their own strengths and weaknesses. Some of the most common types of databases include:

### Relational Databases
Relational databases are the most commonly used type of databases. They store data in tables, with rows representing individual data items and columns representing the attributes of those items. Data is organized in a structured and predictable way and can be queried using SQL (Structured Query Language).

### NoSQL Databases
NoSQL databases are designed to handle large amounts of unstructured data and provide high scalability and performance. They use techniques such as replication, sharding, and distributed data storage to ensure that data is always available and can be read and written to from multiple locations. They are often used in big data and real-time applications.

### Time Series Databases
Time series databases are specifically designed for storing and querying time-stamped data. They are optimized for handling large amounts of data that is generated over time, such as sensor data, financial data, and system metrics. They are often used in IoT and monitoring applications.

### Graph Databases
Graph databases are designed to store and query data that is organized as a graph. They are used to model data that has complex relationships and is best represented as a network of nodes and edges. They are often used in applications such as social networks, recommendation systems, and fraud detection.

## Conclusion
Databases are a fundamental technology that is used to store, organize, and retrieve data in an efficient and organized manner. There are several different types of databases, each with their own strengths and weaknesses, and choosing the right type of database for a specific application is crucial for building robust and efficient systems. Understanding the different types of databases and their capabilities can help developers make the right choice for their specific use case.