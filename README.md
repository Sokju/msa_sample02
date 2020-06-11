
MSA 샘플 유형 2(Spring Cloud + Kubernetes)
======================

# 1. msa2-zuul-server
* Netflix Zuul 예제
* ConfigMap 연동하여 routing 설정 (configmap.yml 참조)
* Port : 9090
* Dockerfile 로 빌드 후 
* Kubernetes 에 yml 로 pod 및 service, Ingress 배포 테스트
* Ingress URL은 zuul2.msasmp02.com 로 테스트 
* Swagger UI 포함

# 2. msa2-svc-member
* 임의의 마이크로서비스 샘플 1
* ConfigMap 연동하여  설정 (configmap.yml 참조)
* Port : 9091
* Dockerfile 로 빌드 후 
* Kubernetes 에 deployment.yml 로 pod 배포 테스트
* RabbitMQ를 통해  msa-svc-order 호출 예제 테스트
* hystrix 테스트(Timeout 유발 circuit-breaker 테스트) 

# 3. msa2-svc-order
* 임의의 마이크로서비스 샘플 2
* Spring Cloud Config 서버 연동
* Port : 9092
* Dockerfile 로 빌드 후 
* Kubernetes 에 yml 로 pod 배포 테스트
* RabbitMQ Subscribe 테스

# 4. msa2-svc-ui
* Frontend UI 용 마이크로서비스  예제(미완성)
* 원래 Spring Security 연동 로그인 처리, 임의의 서비스 2개 호출 예제로 정리
* Spring Cloud Config 서버 연동
* Port : 9093
* Dockerfile 로 빌드 후 
* Kubernetes 에 deployment.yml 로 pod 및 service, Ingress 배포 테스트
* Ingress URL은 frontend.msasmp02.com 로 테스트 

# 5. msa2-turbine-server
* 스트림 수집 Turbine 서버 구성
* Port : 9010
* Dockerfile 로 빌드 후 
* Kubernetes 에 deployment.yml 로 pod 및 service, Ingress 배포 테스트
* Ingress URL은 turbine.msasmp01.com 로 테스트 
* Eureka 없는 환경에선 MQ, Turbine, Dashboard 롤 별도로 구성해야 함

# 6. msa-dashboard-server
* Hystrix Dashboard
* Port : 9011

# 기타
* Zipkin은 현재 Eureka 환경에서만 되는 듯 함
* 유형1과 차이점은 ConfigMap 및 K8s Service 리소스로 대체




