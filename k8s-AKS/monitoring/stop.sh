#!/bin/bash

# Otel Configmap
#kubectl delete monitoring/otel-agent-config.yml

# Monitoring Files
kubectl delete -f monitoring/jaeger-service-deployment.yml
kubectl delete -f monitoring/otel-collector-service-deployment.yml
kubectl delete -f monitoring/microservice-service-monitor.yml

