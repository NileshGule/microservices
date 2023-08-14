#!/bin/bash

alias k=kubectl

# Services
kubectl delete svc jaeger-service-deployment
kubectl delete svc otel-collector-service-deployment
kubectl delete svc authentication-service-deployment
kubectl delete svc account-service-deployment
kubectl delete svc backend-service-deployment
kubectl delete svc forex-service-deployment
kubectl delete svc transaction-service-deployment


# Configmap
kubectl delete configmap otel-collector-configmap

# Create deployment
kubectl delete deployment jaeger-service-deployment
kubectl delete deployment authentication-service-deployment
kubectl delete deployment account-service-deployment
kubectl delete deployment backend-service-deployment
kubectl delete deployment forex-service-deployment
kubectl delete deployment transaction-service-deployment


kubectl delete cm otel-collector-conf && kubectl delete svc otel-collector && kubectl delete deployment otel-collector

