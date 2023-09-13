az aks get-credentials \
--resource-group demo-jug-rg \
--name azure-singapore-cluster \
--overwrite-existing

```powershell

az aks get-credentials `
--resource-group demo-jug-rg `
--name azure-singapore-cluster `
--overwrite-existing

```

## install all operators

```bash

/scripts/helm-install.sh

```

```Powershell

/scripts/helm-install.ps1

```

## Distributed Tracing - Setup OTel Collector For Jaeger, Java Instrumentation and configure Jaeger Oeperator

```bash

kubectl apply -f monitoring/jaeger-operator-config.yaml

kubectl apply -f monitoring/otel-collector-jaeger-config.yaml

kubectl apply -f monitoring/JavaAutoInstrumentation.yaml



```

## Deploy application

```bash

kubectl apply -R -f apps/

```

## Metrics - Configure Prometheus

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

``` bash

az aks get-credentials \
--resource-group demo-azure-singapore-rg \
--name azure-singapore-cluster \
--overwrite-existing

```

```Powershell

az aks get-credentials `
--resource-group demo-azure-singapore-rg `
--name azure-singapore-cluster `
--overwrite-existing

```