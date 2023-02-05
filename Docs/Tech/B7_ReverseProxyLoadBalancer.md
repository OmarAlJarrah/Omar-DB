# Reverse Proxy
A reverse proxy is a server that sits between client devices and a web server and acts as an intermediary between them, forwarding client requests to the web server and returning the server’s responses back to clients.

### Benefits of using a Reverse Proxy
+ **Load balancing**: A reverse proxy can distribute incoming traffic across multiple servers, improving the performance and reliability of a web application.

+ **Security**: A reverse proxy can act as a barrier between client devices and a web server, shielding the server from direct access and preventing attacks such as SQL injection and cross-site scripting.

+ **Content caching**: A reverse proxy can cache frequently requested content, reducing the load on the web server and improving the response time for client requests.

+ **SSL termination**: A reverse proxy can handle SSL encryption and decryption, offloading the processing burden from the web server and improving its performance.

+ **URL rewriting**: A reverse proxy can modify the URLs in client requests, allowing for better control over how a web application is accessed and enabling more flexible URL structures.

## Load Balancers
A load balancer is a device that evenly distributes network or application traffic across multiple servers. This improves the reliability, performance, and availability of applications, services, and websites. Load balancing also helps prevent downtime and reduces the risk of overloading any single server.

### Types of Load Balancers
+ **Network Load Balancer**: This type of load balancer operates at the network layer (OSI layer 4) and is capable of handling millions of requests per second with ultra-low latency.

+ **Application Load Balancer**: This type of load balancer operates at the application layer (OSI layer 7) and provides advanced routing and request handling capabilities for web applications.

+ **Global Load Balancer**: This type of load balancer is designed for high availability and scalability across multiple regions and availability zones.

### Benefits of Load Balancing
+ **Improved reliability**: Load balancing helps prevent downtime by automatically redirecting traffic to healthy servers in the event of a failure.

+ **Increased performance**: Load balancing helps distribute the load across multiple servers, reducing the risk of overloading any single server and improving overall performance.

+ **Better scalability**: Load balancing makes it easy to scale an application or service as demand increases, by simply adding more servers to the pool.

+ **Improved security**: Load balancing can help protect against common attacks such as denial-of-service (DoS) by distributing traffic across multiple servers.

## Spring Cloud Netflix Eureka
Spring Cloud Netflix Eureka is a service registry that allows microservices to discover and communicate with each other. With Eureka, clients can query the registry to find available instances of a service, and the registry can be used to implement load balancing.
![load-balancer](../../assets/load.png)

Fot reference, check the link [here](https://netflixtechblog.com/netflix-shares-cloud-load-balancing-and-failover-tool-eureka-c10647ef95e5).
### Setting up Eureka Server
To set up a Eureka Server, you will need to create a new Spring Boot project and add the following dependencies to your pom.xml file:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```
Next, you will need to add the following configuration to your application.yml file:
```yaml
server:
  port: 8761

eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false
    fetchRegistry: false
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
```
Finally, you will need to add the following annotation to your main class:
```java
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(EurekaServerApplication.class, args);
  }
}
```
### Setting up Eureka Client
To set up a Eureka Client, you will need to create a new Spring Boot project and add the following dependencies to your pom.xml file:
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
Next, you will need to add the following configuration to your application.yml file:
```yaml
server:
  port: 8080

eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
```
Finally, you will need to add the following annotation to your main class:
```java
@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableCaching
@EnableWebSecurity
public class OmarDB {
    public static void main(String[] args) {
        SpringApplication.run(OmarDB.class, args);
    }
}
```
