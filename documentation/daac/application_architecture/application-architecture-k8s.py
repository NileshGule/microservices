# diagram.py
from diagrams.custom import Custom

from diagrams.programming.language import NodeJS
from diagrams.programming.framework import Spring
from diagrams.k8s.compute import Pod
from diagrams.k8s.compute import Deployment
from diagrams.onprem.monitoring import Grafana
from diagrams.onprem.monitoring import Prometheus
from diagrams.onprem.tracing import Jaeger

from diagrams import Diagram, Cluster, Edge

graph_attr = {
    "fontsize": "45",
    "bgcolor": "white"
}

class ApplicationDeployment:
    def __init__(self, service_name, port):
        self.service_name = service_name
        self.port = port

    def service(self) -> Deployment:
        cluster_name = self.service_name + " - Deployment"
        with Cluster(cluster_name):
            with Cluster("Container / Pod"):
                self.otel_jar = otel_agent_jar = Custom("Otel Jar", "../images/jarfile.png")
                service = Spring("Service 1\n Port " + self.port)
                otel_agent_jar = self.otel_jar
                pod_backend = [otel_agent_jar, service]
                service << Edge(label="Auto Instrument") << self.otel_jar
            deployment = Deployment(self.service_name + " Deployment")
            return deployment



with Diagram(name="Microservices Architecture Diagram - K8s", show=False, graph_attr=graph_attr):
    nodeJSwebapp = NodeJS("Node JS Application")
    loki = Custom("Loki Visualisation", "../images/loki.png")

    with Cluster("Open Telemetry Cluster"):
        otel_collector = Custom("OTEL K8s Operator", "../images/otel.png")
        otel_deployment = Deployment("Deployment")

    with Cluster("Monitoring Cluster"):
        metrics = Prometheus("metric")
        metrics << Edge(color="firebrick", style="dashed") << Grafana("monitoring")

    with Cluster("Tracing Backends Applications"):
        jaeger = Jaeger("Jaeger")

    with Cluster("Application Services"):

        backend_app = ApplicationDeployment("Backend Service", "8080")
        backend_deployment = backend_app.service()
        backend_app.otel_jar >> Edge(label="Send Traces") >> otel_collector

        # authentication_app = ApplicationDeployment("Authentication Service", "8081")
        # authentication_deployment = authentication_app.service()
        # authentication_app.otel_jar >> Edge(label="Send Traces") >> otel_collector
        #
        # account_app = ApplicationDeployment("Accounts Service", "8082")
        # account_deployment = account_app.service()
        # account_app.otel_jar >> Edge(label="Send Traces") >> otel_collector
        #
        # forex_app = ApplicationDeployment("Forex Service", "8083")
        # forex_deployment = forex_app.service()
        # forex_app.otel_jar >> Edge(label="Send Traces") >> otel_collector
        #
        # txn_app = ApplicationDeployment("Transaction Service", "8084")
        # txn_deployment = txn_app.service()
        # txn_app.otel_jar >> Edge(label="Send Traces") >> otel_collector

    otel_collector << Edge(label="Collects Metrics") << metrics
    nodeJSwebapp >> Edge(label="Send Traces") >> otel_collector
    otel_collector >> Edge(label="Exports Traces") >> jaeger
    otel_collector >> Edge(label="Send Logs") >> loki
    loki >> Edge(label="Visualise Logs") >> metrics






    # backend_service >> authentication_service
    # backend_service >> account_service
    # backend_service >> transaction_service




