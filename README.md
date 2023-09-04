# spring-microservices
Spring Microservices

- Create SpringBoot Banner
  - https://devops.datenkollektiv.de/banner.txt/index.html

# Steps

## 1. Generate Maven Archetype
- A Maven Archetype is like a blueprint for the project you want to build - contains libraries, APIs, dependencies, version, etc.
- https://maven.apache.org/guides/mini/guide-creating-archetypes.html
- Install Maven
  - `brew install maven`
- Create Archetype
  - `mvn archetype:generate -DgroupId=com.kwebdev -DartifactId=kwebdevservices -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false`

## 2. Create Controller and Service

## 3. Create docker-compose file and run it
  - `docker compose up -d`
    - Terminal: spring-microservices % docker compose up -d
    -  [+] Running 29/2
    -  ✔ postgres 13 layers [⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿]      0B/0B      Pulled                                                                                                                                            26.5s
    -  ✔ pgadmin 14 layers [⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿]      0B/0B      Pulled                                                                                                                                            29.3s
    -  [+] Running 5/5
    -  ✔ Network spring-microservices_postgres   Created                                                                                                                                                       0.0s
    -  ✔ Volume "spring-microservices_pgadmin"   Created                                                                                                                                                       0.0s
    -  ✔ Volume "spring-microservices_postgres"  Created                                                                                                                                                       0.0s
    -  ✔ Container postgres                      Started                                                                                                                                                       0.7s
    -  ✔ Container pgadmin                       Started  
    <br>
  - Verify containers running
    - `docker compose ps`
  
    - | NAME | IMAGE    |           COMMAND         |         SERVICE       |      CREATED      |       STATUS         |     PORTS |
      | ----- | ----- | ----- | -----| ----- | ----- | ----- |
      | pgadmin      |       dpage/pgadmin4  |    "/entrypoint.sh"      |   pgadmin     |        3 minutes ago   |    Up 3 minutes   |     443/tcp, 0.0.0.0:5050->80/tcp |
      | postgres     |       postgres       |     "docker-entrypoint.s…" |  postgres      |      3 minutes ago   |    Up 3 minutes   |     0.0.0.0:5432->5432/tcp |
    
    - in a new browser window go to **`localhost:5050`** and it should pull up a `pgAdmin` page to log in to our `postgres database`
        - **add a new server**
        - Hostname / address is `postgres` because that is the name of the network we defined in our `docker-compose` file for `pgAdmin` and our `postgres DB` to talk to each other, otherwise it would be `localhost`

## 4. Postgres
  - Once the Controller, Service and JPA Repository are up and running, ensure `postgres` is configured on the controller under `resources/application.yml`
  - server:
    port: 8080
    spring:
    application:
    name: customer
    datasource:
    password: password
    url: jdbc:postgresql://localhost:5432/customer
    username: amigoscode
    jpa:
    hibernate:
    ddl-auto: update
    properties:
    hibernate:
    dialect: org.hibernate.dialect.PostgreSQLDialect
    format_sql: true
    show-sql: true 
  - Create the database in the `pgAdmin` at `localhost:5050`
  - In Postman send a `POST` request to insert an object into the DB and verify the insert is successful



## 5. Eureka Microservice Discovery
- ### Service Discovery
  - The process of automatically detecting devices and services on a network so that each microservice does not have to manually keep track and constantly register new IP addresses
      - ![eureka-service-discovery](https://raw.githubusercontent.com/kawgh1/spring-microservices/main/images/eureka-microservice-discovery.png)
  - A Eureka Server manages all of this and acts as a middleman between all the microservices
      - ![eureka-service-discovery-example](https://raw.githubusercontent.com/kawgh1/spring-microservices/main/images/eureka-service-discovery-example.png)

  - As the network scales and the number of APIs and microservices scale, including duplicates, the Eureka Server can quickly become a Single Point of Failure that must be kept running at all times
    - ### `This issue will later be resolved with Kubernetes`
  - Eureka Dashboard at `localhost:8761`
      - ![eureka-service-discovery-example](https://raw.githubusercontent.com/kawgh1/spring-microservices/main/images/eureka-dashboard.png)

## 6. Spring Cloud
- Eureka Server is running on `port: 8761` --> Dashboard `localhost:8761`
- Customer Service is running on `port: 8080`
- Fraud Service is running on `port: 8081`
- pgAdmin is running on `port: 5050` --> Dashboard `localhost:5050`

## 7. OpenFeign
 - OpenFeign is a declarative web service client library for Java, which simplifies the process of making HTTP requests to RESTful web services. It is often used in microservices architectures and other distributed systems where one service needs to communicate with another over HTTP. OpenFeign provides a higher-level, more intuitive way to define and work with HTTP APIs compared to using lower-level HTTP client libraries.

- Here are some key purposes and benefits of **OpenFeign**:

  - **Declarative API Definition**: OpenFeign allows you to define your API interfaces using annotations and Java interfaces. These interfaces define the structure of the HTTP requests and responses in a clean and human-readable way, making it easier to understand and work with APIs.

  - **Integration with Existing Code**: OpenFeign integrates seamlessly with popular Java frameworks like Spring and provides convenient ways to use HTTP-based microservices without writing boilerplate code.

  - **Dynamic Proxy Generation**: OpenFeign generates proxy classes at runtime based on your API interface definitions. These proxies handle the HTTP communication details, including serialization/deserialization of JSON or XML data, headers, and error handling.

  - **Load Balancing**: OpenFeign often works in conjunction with service discovery and load balancing tools (e.g., Netflix Eureka, Ribbon) to distribute requests across multiple instances of a service. This can improve the reliability and scalability of your microservices architecture.

  - **Request and Response Mapping**: You can use annotations like @RequestMapping, @PathVariable, and @RequestParam to map Java objects to HTTP requests and responses, simplifying the conversion process between Java objects and JSON/XML data.

  - **Error Handling**: OpenFeign provides mechanisms for handling errors and exceptions that may occur during API requests. You can define custom error-handling logic to handle different HTTP status codes or error responses.

  - **Interceptors**: You can use interceptors to modify or log requests and responses at runtime, which can be useful for debugging and monitoring purposes.

  - **Request and Response Compression**: OpenFeign supports compression of request and response payloads, reducing the amount of data transferred over the network.

  - Overall, OpenFeign simplifies the process of working with HTTP APIs in Java applications, making it a valuable tool for building and maintaining microservices, client applications, or any system that relies on HTTP-based communication.