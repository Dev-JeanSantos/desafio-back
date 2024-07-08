package com.academy.fourtk.front_services.services

import com.academy.fourtk.front_services.infrastructure.messaging.config.SqsConfig
import io.awspring.cloud.sqs.annotation.SqsListener
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class ContractService(
    @Value("\${cloud.aws.endpoint.queue}")
    private val queue: String,

    private val sqsConfig: SqsConfig
) {
    @SqsListener("contract-service-sqs")
    fun getContractListner(msg: String){
        println("leitura da fila: ${msg}")
    }
}