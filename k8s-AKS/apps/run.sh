#!/bin/bash

# Applications
kubectl apply -f apps/backend-service-deployment.yml
kubectl apply -f apps/account-service-deployment.yml
kubectl apply -f apps/authentication-service-deployment.yml
kubectl apply -f apps/forex-service-deployment.yml
kubectl apply -f apps/transaction-service-deployment.yml