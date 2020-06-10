#kubectl delete -f msa2-svc-member/deployment.yml
#kubectl delete -f msa2-svc-order/deployment.yml
#kubectl delete -f msa2-zuul-server/deployment.yml
#kubectl delete -f msa2-turbine-server/deployment.yml
#kubectl delete -f msa2-dashboard-server/deployment.yml

docker build -t 192.168.100.102:5000/msasmp02/msa2-svc-order:1.0     msa2-svc-order/.
docker build -t 192.168.100.102:5000/msasmp02/msa2-svc-member:1.0    msa2-svc-member/.
docker build -t 192.168.100.102:5000/msasmp02/msa2-zuul-server:1.0   msa2-zuul-server/.
docker build -t 192.168.100.102:5000/msasmp02/msa2-turbine-server:1.0    msa2-turbine-server/.
docker build -t 192.168.100.102:5000/msasmp02/msa2-dashboard-server:1.0    msa2-dashboard-server/.

docker push 192.168.100.102:5000/msasmp02/msa2-svc-order:1.0
docker push 192.168.100.102:5000/msasmp02/msa2-svc-member:1.0
docker push 192.168.100.102:5000/msasmp02/msa2-zuul-server:1.0
docker push 192.168.100.102:5000/msasmp02/msa2-turbine-server:1.0
docker push 192.168.100.102:5000/msasmp02/msa2-dashboard-server:1.0

kubectl rollout restart deployment/msa2-svc-order
kubectl rollout restart deployment/msa2-svc-member
kubectl rollout restart deployment/msa2-zuul-server
kubectl rollout restart deployment/msa2-turbine-server
kubectl rollout restart deployment/msa2-dashboard-server

#kubectl apply -f msa2-svc-order/deployment.yml
#kubectl apply -f msa2-svc-member/deployment.yml
#kubectl apply -f msa2-zuul-server/deployment.yml
#kubectl apply -f msa2-turbine-server/deployment.yml
#kubectl apply -f msa2-dashboard-server/deployment.yml
