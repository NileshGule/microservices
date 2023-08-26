FROM openjdk:17-jdk-slim
#COPY otel/opentelemetry-javaagent.jar /app/
#
##ADD otel/opentelemetry-javaagent.jar .
#ENV JAVA_TOOL_OPTIONS "--add-opens java.base/java.lang=ALL-UNNAMED -javaagent:./app/opentelemetry-javaagent.jar"
#COPY backend-service/target/backend-service-0.0.1-SNAPSHOT.jar /app/app.jar
#CMD ["java", "-jar", "/app/app.jar"]


WORKDIR /app
COPY backend-service/target/*.jar main.jar
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"
