#!/bin/bash

# Applications
kubectl delete -f apps/backend-service-deployment.yml
kubectl delete -f apps/account-service-deployment.yml
kubectl delete -f apps/authentication-service-deployment.yml
kubectl delete -f apps/forex-service-deployment.yml
kubectl delete -f apps/transaction-service-deployment.yml
