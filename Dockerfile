FROM maven:3.9-eclipse-temurin-24 AS builder

WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:24-jdk

WORKDIR /app
COPY --from=builder /build/target/assistant-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
