FROM openjdk:22-ea-4-jdk-oraclelinux8
ARG JAR_FILE=forex-service/target/forex-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar

ENTRYPOINT ["java","-jar", "/app/app.jar", "--spring.profiles.active=k8s"]