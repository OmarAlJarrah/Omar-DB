# BASE Criteria
The BASE criteria is a set of properties that provide a different perspective on the consistency and availability of database systems. The acronym BASE stands for Basically Available, Soft-State, Eventually Consistent. This report will discuss each of these properties and how they differ from the ACID properties.

## Basically Available
The "Basically Available" part of the BASE criteria refers to the availability aspect of database systems, which allows the system to continue functioning and responding to requests, even if it cannot guarantee immediate consistency. This means that the system prioritizes availability over consistency, meaning that it is more important for the system to be up and running and able to handle requests, even if it cannot guarantee immediate consistency.

## Soft-State
The "Soft-State" part of the BASE criteria refers to the mutability of the data in the database. This means that the data in the database can change over time and that there is no guarantee that the data will remain the same over time. This is in contrast to the "Consistency" property of the ACID criteria, which guarantees that the data in the database will remain the same over time.

## Eventually Consistent
The "Eventually Consistent" part of the BASE criteria refers to the eventual convergence of the data in the database to a consistent state. This means that the system may not be immediately consistent, but it will eventually reach a consistent state over time. This is in contrast to the "Isolation" property of the ACID criteria, which guarantees that the data in the database will be immediately consistent.

## BASE in Action
BASE criteria is often used in distributed systems that have high availability as a requirement, such as online stores, social networks, and messaging systems. For example, a social media platform may use a BASE criteria approach, where it prioritizes the availability of the system, allowing users to continue posting and viewing posts even if they may not see the most recent data. In this case, the system will eventually converge to a consistent state, but it may take some time.

## Conclusion
The BASE criteria provides an alternative perspective on the consistency and availability of database systems, which differs from the ACID properties. The BASE criteria prioritizes availability over consistency, allowing systems to continue functioning even if they cannot guarantee immediate consistency. The properties of the BASE criteria are more suitable for distributed systems that have high availability requirements, such as online stores, social networks, and messaging systems. Understanding these properties and how they differ from the ACID properties is crucial for building robust and fault-tolerant distributed systems.