FROM openjdk:22-ea-4-jdk-oraclelinux8
#WORKDIR /app
#COPY otel/opentelemetry-javaagent.jar /app/opentelemetry-javaagent.jar
ARG JAR_FILE=../backend-service/target/backend-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar
