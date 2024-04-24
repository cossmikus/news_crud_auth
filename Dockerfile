# Build stage
FROM maven:3.9.6-eclipse-temurin-11 AS build
WORKDIR /home/app
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM openjdk:23-jdk
COPY --from=build /home/app/target/demo-0.0.1-SNAPSHOT.jar /usr/local/lib/app-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app-1.0.0.jar"]
