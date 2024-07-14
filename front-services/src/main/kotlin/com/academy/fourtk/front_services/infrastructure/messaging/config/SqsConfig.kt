package com.academy.fourtk.front_services.infrastructure.messaging.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import java.net.URI

@Configuration
class SqsConfig {

    @Bean
    fun amazonSQSAsync(): SqsAsyncClient {
        return SqsAsyncClient.builder()
            .endpointOverride(URI.create("http://sqs.us-west-2.localhost.localstack.cloud:4566/000000000000"))
            .region(Region.US_EAST_2)
            .build()
    }
}