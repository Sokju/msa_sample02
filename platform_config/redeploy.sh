kubectl delete -f msa-svc-member/deployment.yml
kubectl delete -f msa-zuul-server/deployment.yml
kubectl delete -f msa-eureka-server/deployment.yml
kubectl delete -f msa-config-server/deployment.yml

docker build -t 192.168.100.102:5000/msasmp01/msa-config-server:1.0 msa-config-server/.
docker build -t 192.168.100.102:5000/msasmp01/msa-eureka-server:1.0 msa-eureka-server/.
docker build -t 192.168.100.102:5000/msasmp01/msa-zuul-server:1.0   msa-zuul-server/.
docker build -t 192.168.100.102:5000/msasmp01/msa-svc-member:1.0    msa-svc-member/.

docker push 192.168.100.102:5000/msasmp01/msa-config-server:1.0
docker push 192.168.100.102:5000/msasmp01/msa-eureka-server:1.0
docker push 192.168.100.102:5000/msasmp01/msa-zuul-server:1.0
docker push 192.168.100.102:5000/msasmp01/msa-svc-member:1.0

#kubectl apply -f msa-config-server/deployment.yml
#kubectl apply -f msa-eureka-server/deployment.yml
#kubectl apply -f msa-svc-member/deployment.yml
#kubectl apply -f msa-zuul-server/deployment.yml
