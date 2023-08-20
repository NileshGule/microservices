#!/bin/bash


kubectl delete cm otel-collector-configmap

# Monitoring Files
kubectl delete -f monitoring/jaeger-service-deployment.yml
kubectl delete -f monitoring/otel-collector-service-deployment.yml
kubectl delete -f monitoring/microservice-service-monitor.yml

# Applications
kubectl delete -f apps/backend-service-deployment.yml
kubectl delete -f apps/account-service-deployment.yml
kubectl delete -f apps/authentication-service-deployment.yml
kubectl delete -f apps/forex-service-deployment.yml
kubectl delete -f apps/transaction-service-deployment.yml
