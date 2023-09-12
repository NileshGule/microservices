az aks get-credentials \
--resource-group demo-jug-rg \
--name azure-singapore-cluster \
--overwrite-existing

## install all operators

```bash

/scripts/helm-install.sh

```

## Setup OTel Collector For Jaeger, Java Instrumentation and configure Jaeger Oeperator

```bash

kubectl apply -f monitoring/otel-collector-jaeger-config.yaml

kubeclt apply -f monitoring/JavaAutoInstrumentation.yaml

kubectl apply -f monitoring/jaeger-operator-config.yaml

```

## Configure Prometheus

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
kubectl apply -f monitoring/otel-collector-jaeger-prometheus-config.yaml

```

We also need to enable the service monitor.

```bash

kubectl apply -f monitoring/microservice-service-monitor.yml

```

## Loki configuration

```bash

kubectl apply -f monitoring/otel-collector-jaeger-prometheus-loki-config.yaml

```

### switch to backup cluster

az aks get-credentials \
--resource-group demo-azure-singapore-rg \
--name azure-singapore-cluster \
--overwrite-existing