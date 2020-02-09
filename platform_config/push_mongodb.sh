docker pull mongo:4.0.8
docker tag mongo:4.0.8 192.168.100.102:5000/msasmp01/mongo:4.0.8
docker push 192.168.100.102:5000/msasmp01/mongo:4.0.8

docker pull rabbitmq:3-management
docker tag rabbitmq:3-management 192.168.100.102:5000/msasmp01/rabbitmq:3-management
docker push 192.168.100.102:5000/msasmp01/rabbitmq:3-management

