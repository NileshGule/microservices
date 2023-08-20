#!/bin/bash

# Prometheus
helm --namespace monitoring uninstall prometheus
helm --namespace monitoring uninstall grafana

#./stop-api-services.sh
