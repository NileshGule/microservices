helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add grafana https://grafana.github.io/helm-charts
helm repo add open-telemetry https://open-telemetry.github.io/opentelemetry-helm-charts
helm repo add jetstack https://charts.jetstack.io
helm repo add jaegertracing https://jaegertracing.github.io/helm-charts

helm repo update

# Cert Manager
helm upgrade --install `
  cert-manager jetstack/cert-manager `
  --version v1.12.0 `
  --set installCRDs=true `
  --set admissionWebhooks.certManager.create=true

# OpenTelemetry Operator
helm upgrade --install my-opentelemetry-operator `
  open-telemetry/opentelemetry-operator

# Jaeger
helm upgrade --install jaeger `
jaegertracing/jaeger-operator

# Prometheus
# helm upgrade --install prometheus \
# prometheus-community/kube-prometheus-stack  \
# --values prometheus/prometheus_values.yaml \
# --set web.enable-remote-write-receiver=true \
# --wait

helm upgrade --install prometheus `
prometheus-community/kube-prometheus-stack  `
--wait

# Loki
helm upgrade --install loki `
grafana/loki-stack  `
--set loki.isDefault=false `
--wait



# Start all the services
#./run-api-services.sh




############ USEFUL COMMANDS  ############

# Port Forwarding/ Exposing Apps to outside world

# kubectl --namespace monitoring port-forward svc/prometheus-grafana 8089:80
# kubectl --namespace monitoring port-forward svc/prometheus-kube-prometheus-prometheus 9090:9090
# Listing all the resources running in Monotring namespace with SELECTOR:"release=prometheus"
# kubectl --namespace monitoring get all --selector "release=prometheus"



