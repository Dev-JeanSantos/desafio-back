export DEFAULT_REGION=us-east-2
export AWS_DEFAULT_REGION=us-east-2
#!/bin/bash
echo "[INFO] - DESAFIO - SETUP LOCALSTACK"

## SNS

echo "[INFO] - CRIAÇÃO SNS"
#aws --no-verify-ssl sns create-topic \
#  --region=us-east-2 \
#  --name transfer-completed-SNS

echo "[INFO] - CRIAÇÃO SQS"
#aws --no-verify-ssl sqs create-queue \
#  --endpoint-url=http://localhost:4566 \
#  --region=us-east-2 \
#  --queue-name contract-service-sqs
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name contract-service-sqs --region us-east-2

echo "[INFO] - CRIAÇÃO SUBSCRIBE"
#aws --no-verify-ssl sns subscribe \
#  --endpoint-url=http://localhost:4566 \
#  --region=us-east-2 \
#  --topic-arn arn:aws:sns:us-east-1:000000000000:transfer-completed-SNS \
#  --protocol sqs \
#  --notification-endpoint arn:aws:sqs:us-east-1:000000000000:data-sqs \
#  --attributes '{"RawMessageDelivery": "true"}'
#
#echo "[INFO] - CRIAÇÃO DO DYNAMODB"
#aws --endpoint-url http://localhost:4566 --region us-east-1 dynamodb create-table --cli-input-json file:///files/dynamo-table.json