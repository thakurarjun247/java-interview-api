## Stage 1: Build the app using Maven
#FROM maven:3.9.6-eclipse-temurin-21 AS builder
#
#WORKDIR /app
#COPY pom.xml .
#COPY src ./src
#
#RUN mvn clean package -DskipTests
#
## Stage 2: Run the app using a lightweight JDK
#FROM eclipse-temurin:21-jdk-alpine
#
#WORKDIR /app
#
## Copy only the final built jar from the builder stage
#COPY --from=builder /app/target/authservice-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java", "-jar", "app.jar"]
