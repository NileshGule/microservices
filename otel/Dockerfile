FROM otel/opentelemetry-collector:0.72.0

ARG CONFIG_FILE=otel-config.yml
COPY ${CONFIG_FILE} /etc/otel-collector-config.yml

CMD [ "--config=/etc/otel-collector-config.yml" ]


#ENTRYPOINT ["java","-jar", "/app.jar", "--spring.profiles.active=k8s"]