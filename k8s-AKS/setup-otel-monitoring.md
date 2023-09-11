#Steps to setup Otel Monitoring

This document describes the steps to setup OpenTelemetry monitoring for AKS cluster. The steps mentioned here are generic and can be applied to other Kubernetes clusters as well.

We will be installing following componets required for monitoring:
- Cert Manager
- OpenTelemetry Operator
- Prometheus kube stack (Prometheus, Grafana, AlertManager)
- Loki

All these will be installed using Helm charts. We will use /scripts/helm-install.sh script to install these components.
Once all the components are installed, we need to do some configurations for the Open Telemetry operator to work correctly.

First and foremost is the configuration for Prometheus to allow remote write. This is done by editing the prometheus object.

```bash

kubectl edit Prometheus prometheus-kube-prometheus-prometheus
    
```

Add extra property in the configuration object

```yaml

enableFeatures:
- remote-write-receiver

```

Next we need to configure the OpenTelemetryCollector to send the metrics to Prometheus. This is done by applying the manifest files.


```bash

kubectl apply -f monitoring/otel-rbac.yaml
kubectl apply -f monitoring/otel-collector-prometheus-loki-config.yaml

```

We also need to enable the service monitor.

```bash

kubectl apply -f monitoring/microservice-service-monitor.yml

```

## Springboot Prometheus dashboard

Refer to the link below for the dashboard to be imported in Grafana.
Springboot : https://grafana.com/grafana/dashboards/11955