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
    - This issue will later be resolved with Kubernetes