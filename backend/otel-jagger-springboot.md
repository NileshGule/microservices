
1. Docker compose file for jagger
2. Command to start Jagger

```shell
docker compose -f docker-compose-jagger.yml up
```

**Access Jagger UI**  
http://localhost:16686/


3. Springboot pom.xml

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
</dependency>
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-otlp</artifactId>
</dependency>
```

4. application.yaml changes

```yaml
spring:
  application:
    name: backend-app
  sleuth:
    otel:
      config:
        trace-id-ratio-based: 1.0
      exporter:
        otlp:
          endpoint: http://localhost:4317

tracing:
  url: http://localhost:4318/v1/traces

management:
  tracing:
    sampling:
      probability: 1.0

```


References
https://refactorfirst.com/distributed-tracing-with-opentelemetry-jaeger-in-spring-boot