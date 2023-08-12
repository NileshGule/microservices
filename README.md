# microservices


#Open Telemetry

https://opentelemetry.io/docs/what-is-opentelemetry/


# Run Java application with Jaeger

Export the variables shown in docker-compose-all.yml for the backend-service

run the command 

- java --add-opens java.base/java.lang=ALL-UNNAMED -javaagent:/app/opentelemetry-javaagent.jar -jar /app/app.jar --spring.profiles.active=k8s