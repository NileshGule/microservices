#!/bin/bash

alias k=kubectl

# Services
kubectl delete svc authentication-service-deployment
kubectl delete svc account-service-deployment
kubectl delete svc backend-service-deployment
kubectl delete svc forex-service-deployment
kubectl delete svc transaction-service-deployment


# Configmap
# kubectl create configmap --from-file

# Create deployment
kubectl delete deployment jaeger-deployment
kubectl delete deployment backend-service-deployment
kubectl delete deployment account-service-deployment
kubectl delete deployment authentication-service-deployment
kubectl delete deployment forex-service-deployment
kubectl delete deployment transaction-service-deployment

