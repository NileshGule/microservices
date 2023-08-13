FROM openjdk:22-ea-4-jdk-oraclelinux8
ARG JAR_FILE=backend-service/target/backend-service-0.0.1-SNAPSHOT.jar
#ARG JAR_FILE=/target/backend-service-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY ${JAR_FILE} /app/app.jar
COPY ../otel/opentelemetry-javaagent.jar /app/