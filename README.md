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
  - Verify containers running
    - `docker compose ps`
      | NAME | IMAGE    |           COMMAND         |         SERVICE       |      CREATED      |       STATUS         |     PORTS |
      | ----- | ----- | -----| -----| -----| -----| -----| -----|
      | pgadmin      |       dpage/pgadmin4  |    "/entrypoint.sh"      |   pgadmin     |        3 minutes ago   |    Up 3 minutes   |     443/tcp, 0.0.0.0:5050->80/tcp |
      | postgres     |       postgres       |     "docker-entrypoint.s…" |  postgres      |      3 minutes ago   |    Up 3 minutes   |     0.0.0.0:5432->5432/tcp |
