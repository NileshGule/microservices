#!/bin/bash

# Configmap
kubectl create configmap otel-collector-configmap  --from-file otel-config.yml

# Create deployment
kubectl apply -f jaeger-service-deployment.yml
kubectl apply -f backend-service-deployment.yml
kubectl apply -f account-service-deployment.yml
kubectl apply -f authentication-service-deployment.yml
kubectl apply -f forex-service-deployment.yml
kubectl apply -f transaction-service-deployment.yml


kubectl apply -f collector-service-deployment.yml