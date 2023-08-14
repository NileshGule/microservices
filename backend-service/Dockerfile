FROM openjdk:22-ea-4-jdk-oraclelinux8
ARG JAR_FILE=./backend-service/target/backend-service-0.0.1-SNAPSHOT.jar
COPY ./otel/opentelemetry-javaagent.jar /app/
WORKDIR /app
COPY ${JAR_FILE} /app/app.jar
