kubectl delete -f ./msa2-svc-member/service.yml
kubectl delete -f ./msa2-svc-member/deployment.yml
kubectl delete -f ./msa2-svc-order/deployment.yml
kubectl delete -f ./msa2-svc-order/service.yml
kubectl delete -f ./msa2-zuul-server/deployment.yml
kubectl delete -f ./msa2-zuul-server/service.yml
kubectl delete -f ./msa2-zuul-server/ingress.yml
kubectl delete -f ./msa2-zipkin-server/deployment.yml
kubectl delete -f ./msa2-zipkin-server/ingress.yml
kubectl delete -f ./msa2-zipkin-server/service.yml
kubectl delete -f ./msa2-turbine-server/deployment.yml
kubectl delete -f ./msa2-turbine-server/service.yml
kubectl delete -f ./msa2-turbine-server/ingress.yml