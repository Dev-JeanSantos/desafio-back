export DEFAULT_REGION=us-east-2
export AWS_DEFAULT_REGION=us-east-2
#!/bin/bash
echo "[INFO] - DESAFIO - SETUP LOCALSTACK"


echo "[INFO] - CRIAÇÃO SQS"
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name contract-service-sqs --region us-east-2

