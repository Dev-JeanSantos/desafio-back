#!/bin/bash
set -x
aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name contract-service-sqs --region us-east-2
set +x