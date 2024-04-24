# Build stage
FROM maven:3.9.6-eclipse-temurin-17 AS build
COPY src /src
COPY pom.xml /
WORKDIR /
RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17-jdk
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/app-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app-1.0.0.jar"]
