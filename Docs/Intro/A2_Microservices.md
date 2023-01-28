# Microservices
A microservice architecture – a variant of the service-oriented architecture structural style – is an architectural pattern that arranges an application as a collection of loosely-coupled, fine-grained services, communicating through lightweight protocols.
One of its goals is that teams can develop and deploy their services independently of others.

This is achieved by the reduction of several dependencies in the code base, allowing for developers to evolve their services with limited restrictions from users, and for additional complexity to be hidden from users. As a consequence, organizations are able to develop software with fast growth and size, as well as use off-the-shelf services more easily. 

Communication requirements are reduced. These benefits come at a cost to maintaining the decoupling. Interfaces need to be designed carefully and treated as a public API. One technique that is used is having multiple interfaces on the same service, or multiple versions of the same service, so as to not disrupt existing users of the code.

## Terminology and Overview
There is no single definition for microservices. A consensus view has evolved over time in the industry. Some of the defining characteristics that are frequently cited include:

Services in a microservice architecture are often processes that communicate over a network to fulfill a goal using technology-agnostic protocols such as HTTP.
Services are organized around business capabilities.

Services can be implemented using different programming languages, databases, hardware and software environments, depending on what fits best.
Services are small in size, messaging-enabled, bounded by contexts, autonomously developed, independently deployable, decentralized and built and released with automated processes.

A microservice is not a layer within a monolithic application (example, the web controller, or the backend-for-frontend). Rather, it is a self-contained piece of business functionality with clear interfaces, and may, through its own internal components, implement a layered architecture. From a strategical perspective, microservice architecture essentially follows the Unix philosophy of "Do one thing and do it well". Martin Fowler describes a microservices-based architecture as having the following properties:

+ Lends itself to a continuous delivery software development process. A change to a small part of the application only requires rebuilding and redeploying only one or a small number of services.
+ Adheres to principles such as fine-grained interfaces (to independently deployable services), business-driven development (e.g. domain-driven design).
+ It is common for microservices architectures to be adopted for cloud-native applications, serverless computing, and applications using lightweight container deployment. 

According to Fowler, because of the large number (when compared to monolithic application implementations) of services, decentralized continuous delivery and DevOps with holistic service monitoring are necessary to effectively develop, maintain, and operate such applications.

A consequence of (and rationale for) following this approach is that the individual microservices can be individually scaled. In the monolithic approach, an application supporting three functions would have to be scaled in its entirety even if only one of these functions had a resource constraint. With microservices, only the microservice supporting the function with resource constraints needs to be scaled out, thus providing resource and cost optimization benefits.

## Benefits of decomposing services

The benefit of decomposing an application into different smaller services are numerous:

+ **Modularity**: This makes the application easier to understand, develop, test, and become more resilient to architecture erosion. This benefit is often argued in comparison to the complexity of monolithic architectures.
+ **Scalability**: Since microservices are implemented and deployed independently of each other, i.e. they run within independent processes, they can be monitored and scaled independently.
+ **Integration of heterogeneous and legacy systems**: microservices is considered a viable means for modernizing existing monolithic software application. There are experience reports of several companies who have successfully replaced (parts of) their existing software with microservices or are in the process of doing so. The process for Software modernization of legacy applications is done using an incremental approach.
+ **Distributed development**: it parallelizes development by enabling small autonomous teams to develop, deploy and scale their respective services independently. It also allows the architecture of an individual service to emerge through continuous refactoring. Microservice-based architectures facilitate continuous integration, continuous delivery and deployment.

## Advantages of Microservices
+ **Scalability**: Microservices can be scaled independently, which allows for better resource utilization and cost efficiency.

+ **Flexibility**: Microservices can be developed and deployed independently, which allows for faster development and deployment cycles.

+ **Resilience**: Microservices can continue to function even if one or more services fail, which improves the overall availability and fault-tolerance of the system.

+ **Maintainability**: Microservices are smaller and simpler than monolithic systems, which makes them easier to understand and maintain.

+ **Technology Heterogeneity**: Microservices can be implemented in different programming languages and technologies, which allows for more flexibility and innovation in the development process.

## Challenges of Microservices
+ **Complexity**: Microservices introduce additional complexity in terms of communication, coordination and management of services.

+ **Data consistency**: Microservices can have different data storage and different data models, which can make it more difficult to maintain consistency across the system.

+ **Testing**: Microservices can be more difficult to test, as they require more coordination and integration testing than monolithic systems.

+ **Deployment**: Microservices can be more difficult to deploy, as they require more coordination and management than monolithic systems.

## Conclusion
Microservices is an architectural style that allows for building software systems that are composed of small, independently deployable services. It offers several advantages over monolithic systems, such as scalability, flexibility, resilience, and maintainability. However, it also introduces additional complexity in terms of communication, coordination, and management of services. Understanding the advantages and challenges of microservices can help developers to make the right choice for their specific use case and design the systems accordingly.