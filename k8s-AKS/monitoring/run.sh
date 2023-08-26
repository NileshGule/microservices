#!/bin/bash

# Configmap
kubectl apply -f monitoring/otel-agent-config.yml

# Monitoring Files
kubectl apply -f monitoring/jaeger-service-deployment.yml
kubectl apply -f monitoring/otel-collector-service-deployment.yml
kubectl apply -f monitoring/microservice-service-monitor.yml