#!/bin/bash

# Otel CM, SVC, Deployment
kubectl delete cm otel-collector-configmap
# Create deployment, SVC
kubectl delete -f monitoring/jaeger-service-deployment.yml
kubectl delete -f monitoring/otel-collector-service-deployment.yml
kubectl delete -f monitoring/microservice-service-monitor.yml


kubectl delete -f apps/backend-service-deployment.yml
kubectl delete -f apps/account-service-deployment.yml
kubectl delete -f apps/authentication-service-deployment.yml
kubectl delete -f apps/forex-service-deployment.yml
kubectl delete -f apps/transaction-service-deployment.yml
