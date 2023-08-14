FROM openjdk:22-ea-4-jdk-oraclelinux8
#ARG JAR_FILE=account-service/target/account-service-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=./account-service/target/account-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/app.jar

#COPY /target/account-service-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/app.jar", "--spring.profiles.active=k8s"]