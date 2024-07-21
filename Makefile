

deploys:
	kubectl apply -f deployment.yaml && kubectl apply -f service.yaml

setup:
	docker-compose -p replicate up -d