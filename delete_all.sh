kubectl delete -f msa-svc-member/deployment.yml
kubectl delete -f msa-svc-order/deployment.yml
kubectl delete -f msa-zuul-server/deployment.yml
kubectl delete -f msa-zuul-server/service.yml
kubectl delete -f msa-zuul-server/ingress.yml
kubectl delete -f msa-eureka-server/deployment.yml
kubectl delete -f msa-eureka-server/service.yml
kubectl delete -f msa-eureka-server/ingress.yml
kubectl delete -f msa-config-server/deployment.yml
kubectl delete -f msa-config-server/service.yml
kubectl delete -f msa-config-server/ingress.yml
kubectl delete -f msa-turbine-server/deployment.yml
kubectl delete -f msa-turbine-server/service.yml
kubectl delete -f msa-turbine-server/ingress.yml
kubectl delete -f msa-zipkin-server/deployment.yml
kubectl delete -f msa-zipkin-server/service.yml
kubectl delete -f msa-zipkin-server/ingress.yml

