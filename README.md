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