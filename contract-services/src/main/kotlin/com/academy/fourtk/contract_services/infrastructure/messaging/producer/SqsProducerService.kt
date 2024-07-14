package com.academy.fourtk.contract_services.infrastructure.messaging.producer


import com.amazonaws.services.sqs.AmazonSQSAsync
import io.awspring.cloud.messaging.core.QueueMessagingTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SqsProducerService(
    @Value("\${cloud.aws.endpoint.sqs}")
    private val sqsEndpoint: String,
    private val amazonSQSAsync: AmazonSQSAsync
) {
    private val queueMessagingTemplate: QueueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync)
    fun sendMessage(queueName: String, message: String) {
        val queueUrl = "$sqsEndpoint/$queueName"
        queueMessagingTemplate.convertAndSend(queueUrl, message)
    }
}