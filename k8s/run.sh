#!/bin/bash

# Configmap
# kubectl create configmap --from-file

# Create deployment
kubectl apply -f jaeger-service-deployment.yml
kubectl apply -f backend-service-deployment.yml
kubectl apply -f account-service-deployment.yml
kubectl apply -f authentication-service-deployment.yml
kubectl apply -f forex-service-deployment.yml
kubectl apply -f transaction-service-deployment.yml
