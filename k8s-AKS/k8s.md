# One time set up activities
https://www.baeldung.com/spring-boot-minikube
```shell
$ kubectl config use-context minikube
$ kubectl cluster-info
```

# Ensure your minikube is able to use local build images.

```shell
$ eval $(minikube docker-env)
```

## Rebuild you docker images again from the same sheell terminal session
```shell
$ cd docker-scripts
$ cd ./all.ps1
$ cd k8s
```
`

## Verify the images do exist

```shell
$ minikube image ls --format table
````


# Exposing services to outside world if you are running on Minikube
```shell
$ minikube service backend-service-deployment

# In another terminal
$ minikube service jaeger-service-deployment
```


# Exposing services to outside world if you are running on Docker Desktop
```shell
kubectl --namespace monitoring port-forward svc/prometheus-kube-prometheus-prometheus 9090:9090 &
kubectl --namespace monitoring port-forward svc/prometheus-kube-prometheus-prometheus 8089:8089 &
kubectl port-forward svc/jaeger-service-deployment 16686:16686 &
kubectl port-forward svc/otel-collector-service-deployment 4317:4317 &
kubectl port-forward svc/otel-collector-service-deployment 4318:4318  &
kubectl port-forward svc/backend-service-deployment 8080:8080 &
kubectl port-forward svc/authentication-service-deployment 8081:8081 &
kubectl port-forward svc/account-service-deployment 8082:8082 &
kubectl port-forward svc/forex-service-deployment 8083:8083 &
kubectl port-forward svc/transaction-service-deployment 8084:8084 &
```

