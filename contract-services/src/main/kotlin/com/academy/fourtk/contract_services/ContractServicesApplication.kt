package com.academy.fourtk.contract_services

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.retry.annotation.EnableRetry

@SpringBootApplication
@Configuration
@EnableFeignClients( "com.academy.fourtk.contract_services.resources.gateways.*")
@ComponentScan("com.academy.fourtk.*")
@EnableRetry
class ContractServicesApplication

fun main(args: Array<String>) {
	runApplication<ContractServicesApplication>(*args)
}
