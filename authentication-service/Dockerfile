FROM openjdk:22-ea-4-jdk-oraclelinux8
ARG JAR_FILE=authentication-service/target/authentication-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java","-jar", "/app.jar", "--spring.profiles.active=k8s"]