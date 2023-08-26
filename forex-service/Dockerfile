FROM openjdk:17-jdk-slim
WORKDIR /app
COPY otel/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"
COPY forex-service/target/*.jar /app/app.jar