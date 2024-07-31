

deploys:
	kubectl apply -f deployment.yaml && kubectl apply -f service.yaml

setup:
	docker-compose -p replicate up -d


build-images:
	mvn -f ./master-service wrapper:wrapper
	docker build -f ./master-service/Dockerfile -t master-replicate:latest ./master-service
	mvn -f ./slave-service wrapper:wrapper
	docker build -f ./slave-service/Dockerfile -t slave-replicate:latest ./slave-service