#!/bin/bash

# Configmap
kubectl apply -f monitoring/otel-agent-config.yml

# Monitoring Files
kubectl apply -f monitoring/jaeger-service-deployment.yml
kubectl apply -f monitoring/otel-collector-service-deployment.yml
kubectl apply -f monitoring/microservice-service-monitor.yml

# Prometheus related deployment
kubectl apply -f monitoring/otel-rbac.yaml
kubectl apply -f monitoring/otel-collector-jaerger-prometheus-loki-config.yaml

# Refer to following link for setting up Jaeger operator on Kubernetes
# https://techblog.cisco.com/blog/getting-started-with-opentelemetry
# https://github.com/open-telemetry/opentelemetry-demo/blob/main/kubernetes/opentelemetry-demo.yaml