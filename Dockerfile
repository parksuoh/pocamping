FROM openjdk:17-alpine AS builder

ARG JAR_FILE=/build/libs/camping-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java","-jar", "/app.jar"]


