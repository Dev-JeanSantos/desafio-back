package com.academy.fourtk.contract_services.infrastructure.messaging.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration
class SqsConfig {
    @Bean
    fun amazonSQSAsync(): AmazonSQSAsync {
        return AmazonSQSAsyncClientBuilder.standard()
            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("test", "test")))
            .build()
    }
}