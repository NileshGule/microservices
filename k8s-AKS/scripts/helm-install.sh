#!/bin/bash
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update


# Prometheus
helm upgrade --install prometheus \
prometheus-community/kube-prometheus-stack  \
--values prometheus/prometheus_values.yaml \
--create-namespace \
--wait \
--namespace monitoring

# Install Grafana
helm upgrade --install grafana bitnami/grafana \
--set admin.user=admin \
--set metrics.enabled=true \
--set metrics.serviceMonitor.enabled=true \
--create-namespace \
--wait \
--namespace monitoring

# Start all the services
#./run-api-services.sh



"""
############ USEFUL COMMANDS  ############

# Port Forwarding/ Exposing Apps to outside world

# kubectl --namespace monitoring port-forward svc/prometheus-grafana 8089:80
# kubectl --namespace monitoring port-forward svc/prometheus-kube-prometheus-prometheus 9090:9090
# Listing all the resources running in Monotring namespace with SELECTOR:"release=prometheus"
#kubectl --namespace monitoring get all --selector "release=prometheus"

"""


